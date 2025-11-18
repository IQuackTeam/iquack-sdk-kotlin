package ru.iquack.sdk.core.caller

import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import ru.iquack.sdk.core.RefreshTokenIsNotProvided
import ru.iquack.sdk.core.TokenProvider

internal class KtorCaller(
    private val tokenProvider: TokenProvider,
    private val json: Json,
    private val refreshCaller: suspend () -> Boolean
) : Caller {// Auto refresh token logic can be implemented here in the future

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(json)
        }
    }

    override suspend fun <T> get(url: String, deserialize: (String) -> T): Result<T> = apiCall {
        client.get(url) {
            setupHeaders()
        }.bodyAsText().let(deserialize)
    }

    override suspend fun <T> post(url: String, body: Any?, deserialize: (String) -> T): Result<T> = apiCall {
        client.post(url) {
            setupHeaders()
            if (body != null) setBody(body)
        }.bodyAsText().let(deserialize)
    }

    override suspend fun <T> put(url: String, body: Any?, deserialize: (String) -> T): Result<T> = apiCall {
        client.put(url) {
            setupHeaders()
            if (body != null) setBody(body)
        }.bodyAsText().let(deserialize)
    }

    override suspend fun <T> patch(url: String, body: Any?, deserialize: (String) -> T): Result<T> = apiCall {
        client.patch(url) {
            setupHeaders()
            if (body != null) setBody(body)
        }.bodyAsText().let(deserialize)
    }

    override suspend fun <T> delete(url: String, deserialize: (String) -> T): Result<T> = apiCall {
        client.delete(url) {
            setupHeaders()
        }.bodyAsText().let(deserialize)
    }

    private suspend fun HttpRequestBuilder.setupHeaders() {
        tokenProvider.getAccessToken()?.let { header("Authorization", "Bearer $it") }
        contentType(ContentType.Application.Json)
    }

    private suspend inline fun <T> apiCall(block: suspend () -> T): Result<T> {
        val result = runCatching { block() }

        val exception = result.exceptionOrNull()
        if (exception is ClientRequestException && exception.response.status.value == 401) {
            val refreshToken = tokenProvider.getRefreshToken()
            return if (refreshToken != null) {
                val refreshed = runCatching { refreshCaller() }.getOrDefault(false)
                when(refreshed){
                    true -> runCatching { block() }
                    false -> Result.failure(exception)
                }
            } else {
                Result.failure(RefreshTokenIsNotProvided())
            }
        }

        return result
    }
}

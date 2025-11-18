package ru.iquack.sdk.core.auth.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.Instant


@Serializable
data class LoginResponse (
    val accessToken: String? = null,
    val refreshToken: String? = null,
    @SerialName("accessExpAt") internal val accessExpAtRaw: String? = null, //Format: 2025-10-28T01:27:00.625621Z
    @SerialName("refreshExpAt") internal val refreshExpAtRaw: String? = null //Format: 2025-10-28T01:27:00.625621Z
) {

    val accessExpAt: Instant?
        get() = accessExpAtRaw?.let { Instant.parse(it) }

    val refreshExpAt: Instant?
        get() = refreshExpAtRaw?.let { Instant.parse(it) }

}
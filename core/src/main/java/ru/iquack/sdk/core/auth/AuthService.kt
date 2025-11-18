package ru.iquack.sdk.core.auth

import kotlinx.serialization.json.Json
import ru.iquack.sdk.core.RefreshTokenIsNotProvided
import ru.iquack.sdk.core.TokenProvider
import ru.iquack.sdk.core.auth.models.DisconnectOAuthRequest
import ru.iquack.sdk.core.auth.models.RefreshTokenRequest
import ru.iquack.sdk.core.auth.models.ConnectOAuthRequest
import ru.iquack.sdk.core.auth.models.ResetPasswordConfirmRequest
import ru.iquack.sdk.core.auth.models.ResetPasswordWithPhoneRequest
import ru.iquack.sdk.core.auth.models.ResetPasswordWithEmailRequest
import ru.iquack.sdk.core.auth.models.RegisterWithPhoneRequest
import ru.iquack.sdk.core.auth.models.RegisterWithEmailRequest
import ru.iquack.sdk.core.auth.models.ConfirmEmailRequest
import ru.iquack.sdk.core.auth.models.ConfirmPhoneRequest
import ru.iquack.sdk.core.auth.models.ChangePasswordRequest
import ru.iquack.sdk.core.auth.models.ChangePhoneRequest
import ru.iquack.sdk.core.auth.models.ChangeEmailRequest
import ru.iquack.sdk.core.auth.models.CompleteRegisterRequest
import ru.iquack.sdk.core.auth.models.VerifyResponse
import ru.iquack.sdk.core.auth.models.LoginWithOAuthRequest
import ru.iquack.sdk.core.auth.models.LoginWithPhoneRequest
import ru.iquack.sdk.core.auth.models.LoginResponse
import ru.iquack.sdk.core.auth.models.LoginWithEmailRequest
import ru.iquack.sdk.core.auth.models.LogoutRequest
import ru.iquack.sdk.core.caller.Caller

class AuthService internal constructor(
    private val caller: Caller,
    private val json: Json,
    private val tokenProvider: TokenProvider,
    baseUrl: String,
) {

    private val baseAuthUrl = "$baseUrl/auth"

    // ========== Login methods ==========

    suspend fun loginWithEmail(req: LoginWithEmailRequest): Result<LoginResponse> =
        caller.post("$baseAuthUrl/email/login", req) {
            json.decodeFromString(LoginResponse.serializer(), it)
        }.storeTokens()

    suspend fun loginWithPhone(req: LoginWithPhoneRequest): Result<LoginResponse> =
        caller.post("$baseAuthUrl/phone/login", req) {
            json.decodeFromString(LoginResponse.serializer(), it)
        }.storeTokens()

    suspend fun loginWithOAuth(req: LoginWithOAuthRequest): Result<LoginResponse> =
        caller.post("$baseAuthUrl/oauth/login", req) {
            json.decodeFromString(LoginResponse.serializer(), it)
        }.storeTokens()


    // ========== Change Auth methods ==========

    suspend fun changeEmail(req: ChangeEmailRequest): Result<VerifyResponse> =
        caller.post("$baseAuthUrl/email/change", req) {
            json.decodeFromString(VerifyResponse.serializer(), it)
        }

    suspend fun changePhone(req: ChangePhoneRequest): Result<VerifyResponse> =
        caller.post("$baseAuthUrl/phone/change", req) {
            json.decodeFromString(VerifyResponse.serializer(), it)
        }

    suspend fun changePassword(req: ChangePasswordRequest): Result<Unit> =
        caller.post("$baseAuthUrl/password/change", req) {}



    // ========== Confirm methods ==========

    suspend fun confirmEmail(req: ConfirmEmailRequest): Result<Unit> =
        caller.post("$baseAuthUrl/email/confirm", req) {}

    suspend fun confirmPhone(req: ConfirmPhoneRequest): Result<Unit> =
        caller.post("$baseAuthUrl/phone/confirm", req) {}


    // ========== Register methods ==========

    suspend fun registerWithEmail(req: RegisterWithEmailRequest): Result<VerifyResponse> =
        caller.post("$baseAuthUrl/email/register", req) {
            json.decodeFromString(VerifyResponse.serializer(), it)
        }

    suspend fun registerWithPhone(req: RegisterWithPhoneRequest): Result<VerifyResponse> =
        caller.post("$baseAuthUrl/phone/register", req) {
            json.decodeFromString(VerifyResponse.serializer(), it)
        }

    suspend fun completeRegister(req: CompleteRegisterRequest): Result<LoginResponse> =
        caller.post("$baseAuthUrl/register/complete", req) {
            json.decodeFromString(LoginResponse.serializer(), it)
        }.storeTokens()


    // ========== Reset methods ==========

    suspend fun resetPasswordWithEmail(req: ResetPasswordWithEmailRequest): Result<VerifyResponse> =
        caller.post("$baseAuthUrl/password/reset/email", req) {
            json.decodeFromString(VerifyResponse.serializer(), it)
        }

    suspend fun resetPasswordWithPhone(req: ResetPasswordWithPhoneRequest): Result<VerifyResponse> =
        caller.post("$baseAuthUrl/password/reset/phone", req) {
            json.decodeFromString(VerifyResponse.serializer(), it)
        }

    suspend fun resetPasswordConfirm(req: ResetPasswordConfirmRequest): Result<Unit> =
        caller.post("$baseAuthUrl/password/reset/confirm", req) {}


    // ========== OAuth control methods ==========

    suspend fun connectOAuth(req: ConnectOAuthRequest): Result<Unit> =
        caller.post("$baseAuthUrl/oauth/connect", req) {}

    suspend fun disconnectOAuth(req: DisconnectOAuthRequest): Result<Unit> =
        caller.post("$baseAuthUrl/oauth/disconnect", req) {}


    // ========== Other methods ==========

    suspend fun refreshToken(): Result<LoginResponse>{
        val refreshToken = tokenProvider.getRefreshToken()
            ?: return Result.failure(RefreshTokenIsNotProvided())

        val result = caller.post("$baseAuthUrl/refresh", RefreshTokenRequest(refreshToken)) {
            json.decodeFromString(LoginResponse.serializer(), it)
        }.storeTokens()

        return result.onSuccess {
            tokenProvider.onNewAccessToken(it.accessToken)
            tokenProvider.onNewRefreshToken(it.refreshToken)
        }
    }

    suspend fun logout(): Result<Unit>{
        val result = caller.post("$baseAuthUrl/logout", LogoutRequest(
            refreshToken = tokenProvider.getRefreshToken()
        )) {}
        result.onSuccess {
            tokenProvider.onNewAccessToken(null)
            tokenProvider.onNewRefreshToken(null)
        }
        return result
    }


    private suspend fun Result<LoginResponse>.storeTokens(): Result<LoginResponse> = onSuccess {
        tokenProvider.onNewAccessToken(it.accessToken)
        tokenProvider.onNewRefreshToken(it.refreshToken)
    }
}

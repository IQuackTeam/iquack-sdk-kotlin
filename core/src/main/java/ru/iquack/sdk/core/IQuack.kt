package ru.iquack.sdk.core

import kotlinx.serialization.json.Json
import ru.iquack.sdk.core.auth.AuthService
import ru.iquack.sdk.core.caller.Caller
import ru.iquack.sdk.core.caller.KtorCaller
import ru.iquack.sdk.core.notifications.NotificationsService
import ru.iquack.sdk.core.roles.RolesService
import ru.iquack.sdk.core.templates.TemplatesService
import ru.iquack.sdk.core.user_profiles.UserProfilesService
import ru.iquack.sdk.core.user_roles.UserRolesService
import ru.iquack.sdk.core.users.UsersService


class IQuack(
    private val tokenProvider: TokenProvider,
){

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }
    private val caller: Caller = KtorCaller(tokenProvider, json, ::refreshCaller)

    // ID Services
    val auth: AuthService by lazy { AuthService(caller, json, tokenProvider, BASE_ID_URL) }
    val roles by lazy { RolesService(caller, json, BASE_ID_URL) }
    val users: UsersService by lazy { UsersService(caller, json, BASE_ID_URL) }
    val userRoles by lazy { UserRolesService(caller, json, BASE_ID_URL) }
    val userProfiles by lazy { UserProfilesService(caller, json, BASE_ID_URL) }

    // Notifications Services
    val templates by lazy { TemplatesService(caller, json, BASE_NOTIFY_URL) }
    val notifications by lazy { NotificationsService(caller, json, BASE_NOTIFY_URL) }

    // Wallet Services
    // val balance = TODO()
    // val balanceOperation = TODO()

    private suspend fun refreshCaller(): Boolean =
        auth.refreshToken().isSuccess

    companion object{
        private const val BASE_ID_URL = "https://auth.iquack.ru"
        private const val BASE_NOTIFY_URL = "https://notify.iquack.ru"
        private const val BASE_WALLET_URL = "https://wallet.iquack.ru"
    }
}

interface TokenProvider {

    suspend fun getAccessToken(): String?
    suspend fun getRefreshToken(): String?

    suspend fun onNewAccessToken(token: String?)
    suspend fun onNewRefreshToken(token: String?)
}
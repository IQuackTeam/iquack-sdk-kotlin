package ru.iquack.sdk.android

import android.accounts.AbstractAccountAuthenticator
import android.accounts.Account
import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.content.Context
import android.os.Bundle
import kotlinx.coroutines.runBlocking
import ru.iquack.sdk.core.IQuack

internal class IQuackAuthenticator(private val context: Context) : AbstractAccountAuthenticator(context) {

    override fun addAccount(
        response: AccountAuthenticatorResponse,
        accountType: String,
        authTokenType: String?,
        requiredFeatures: Array<out String>?,
        options: Bundle?
    ): Bundle {
        return Bundle().apply {
            putInt(AccountManager.KEY_ERROR_CODE, 1)
            putString(AccountManager.KEY_ERROR_MESSAGE, "No token, login required")
        }
    }

    override fun getAuthToken(
        response: AccountAuthenticatorResponse,
        account: Account,
        authTokenType: String,
        options: Bundle?
    ): Bundle {
        val am = AccountManager.get(context)
        val token = am.peekAuthToken(account, authTokenType)

        return if (token != null) {
            Bundle().apply {
                putString(AccountManager.KEY_ACCOUNT_NAME, account.name)
                putString(AccountManager.KEY_ACCOUNT_TYPE, account.type)
                putString(AccountManager.KEY_AUTHTOKEN, token)
            }
        } else {
            Bundle().apply {
                putInt(AccountManager.KEY_ERROR_CODE, 1)
                putString(AccountManager.KEY_ERROR_MESSAGE, "No token, login required")
            }
        }
    }


    override fun confirmCredentials(
        response: AccountAuthenticatorResponse,
        account: Account,
        options: Bundle?
    ): Bundle? = runBlocking {
        val tokenProvider = AndroidTokenProvider(context)
        tokenProvider.getAccessToken()?: return@runBlocking Bundle().apply {
            putBoolean(AccountManager.KEY_BOOLEAN_RESULT, false)
        }

        val api = IQuack(tokenProvider)
        val result = api.users.getUser()

        Bundle().apply {
            putBoolean(AccountManager.KEY_BOOLEAN_RESULT, result.isSuccess)
            if (!result.isSuccess) putString(AccountManager.KEY_ERROR_MESSAGE, "Session invalid or token expired")
        }
    }


    override fun getAuthTokenLabel(authTokenType: String): String = authTokenType
    override fun editProperties(response: AccountAuthenticatorResponse, accountType: String): Bundle = Bundle()
    override fun updateCredentials(response: AccountAuthenticatorResponse, account: Account, authTokenType: String?, options: Bundle?): Bundle? = null
    override fun hasFeatures(response: AccountAuthenticatorResponse, account: Account, features: Array<out String>?): Bundle =
        Bundle().apply { putBoolean(AccountManager.KEY_BOOLEAN_RESULT, false) }
}


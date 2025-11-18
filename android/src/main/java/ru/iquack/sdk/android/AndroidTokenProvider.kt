package ru.iquack.sdk.android

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import ru.iquack.sdk.core.TokenProvider


/***
 * Token provider that uses Android AccountManager to store tokens.
 * Creates an account of type [ACCOUNT_TYPE] if it doesn't exist.
 * Stores access token as auth token and refresh token as user data.
 *
 * Needs an authenticator to be registered in the app.
 * Manifest snippet:
 * <service
 *     android:name="ru.iquack.id.android.IQuackAuthenticatorService"
 *     android:exported="true"
 *     android:permission="android.permission.BIND_ACCOUNT_AUTHENTICATOR">
 *     <intent-filter>
 *         <action android:name="android.accounts.AccountAuthenticator" />
 *     </intent-filter>
 *
 *     <meta-data
 *         android:name="android.accounts.AccountAuthenticator"
 *         android:resource="@xml/authenticator" />
 * </service>
 *
 * Where @xml/authenticator is a resource file with the following content:
 * <account-authenticator
 *     xmlns:android="http://schemas.android.com/apk/res/android"
 *     android:accountType="ru.iquack.id.account"
 *     android:icon="@mipmap/ic_launcher"
 *     android:label="@string/app_name"
 *     android:smallIcon="@mipmap/ic_launcher"
 *     android:accountPreferences="@xml/account_preferences" />
 */
class AndroidTokenProvider(
    context: Context
): TokenProvider {

    private val accountManager = AccountManager.get(context)
    private val account = getOrCreateAccount()

    override suspend fun getAccessToken(): String? =
        accountManager.peekAuthToken(account, TOKEN_TYPE_ACCESS)

    override suspend fun getRefreshToken(): String? =
        accountManager.getUserData(account, KEY_REFRESH_TOKEN)

    override suspend fun onNewAccessToken(token: String?) {
        if (token == null)
            accountManager.invalidateAuthToken(ACCOUNT_TYPE, null)
        else
            accountManager.setAuthToken(account, TOKEN_TYPE_ACCESS, token)
    }

    override suspend fun onNewRefreshToken(token: String?) {
        accountManager.setUserData(account, KEY_REFRESH_TOKEN, token)
    }

    private fun getOrCreateAccount(): Account {
        val existing = accountManager.getAccountsByType(ACCOUNT_TYPE).firstOrNull()
        return existing ?: Account(ACCOUNT_NAME, ACCOUNT_TYPE).also {
            accountManager.addAccountExplicitly(it, null, null)
        }
    }

    companion object {
        const val ACCOUNT_TYPE = "ru.iquack.id.account"
        const val ACCOUNT_NAME = "IQuack ID"
        const val TOKEN_TYPE_ACCESS = "access_token"
        const val KEY_REFRESH_TOKEN = "refresh_token"
    }

}
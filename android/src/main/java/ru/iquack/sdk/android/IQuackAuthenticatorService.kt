package ru.iquack.sdk.android

import android.app.Service
import android.content.Intent
import android.os.IBinder

class IQuackAuthenticatorService : Service() {

    private lateinit var authenticator: IQuackAuthenticator

    override fun onCreate() {
        authenticator = IQuackAuthenticator(this)
    }

    override fun onBind(intent: Intent?): IBinder? =
        authenticator.iBinder
}
package ru.iquack.sdk.core.auth.models

import kotlinx.serialization.Serializable


@Serializable
data class ConnectOAuthRequest (
    val provider: String? = null,
    val oauthToken: String? = null
)
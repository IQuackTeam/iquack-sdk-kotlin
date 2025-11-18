package ru.iquack.sdk.core.auth.models

import kotlinx.serialization.Serializable


@Serializable
data class LoginWithOAuthRequest (
    val provider: String? = null,
    val oauthToken: String? = null
)
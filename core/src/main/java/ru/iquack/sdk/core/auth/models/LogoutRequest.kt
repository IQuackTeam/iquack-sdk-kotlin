package ru.iquack.sdk.core.auth.models

import kotlinx.serialization.Serializable

@Serializable
data class LogoutRequest(
    val refreshToken: String? = null
)

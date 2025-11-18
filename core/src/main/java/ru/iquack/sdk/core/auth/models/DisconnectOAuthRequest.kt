package ru.iquack.sdk.core.auth.models

import kotlinx.serialization.Serializable



@Serializable
data class DisconnectOAuthRequest (
    val provider: String? = null
)
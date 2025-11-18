package ru.iquack.sdk.core.auth.models

import kotlinx.serialization.Serializable


@Serializable
internal data class RefreshTokenRequest (
    val refreshToken: String? = null
)
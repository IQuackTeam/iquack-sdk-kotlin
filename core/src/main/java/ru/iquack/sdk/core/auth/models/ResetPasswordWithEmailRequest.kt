package ru.iquack.sdk.core.auth.models

import kotlinx.serialization.Serializable


@Serializable
data class ResetPasswordWithEmailRequest (
    val email: String? = null
)
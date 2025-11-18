package ru.iquack.sdk.core.auth.models

import kotlinx.serialization.Serializable


@Serializable
data class ResetPasswordConfirmRequest (
    val verificationToken: String? = null,
    val code: String? = null,
    val newPassword: String? = null
)
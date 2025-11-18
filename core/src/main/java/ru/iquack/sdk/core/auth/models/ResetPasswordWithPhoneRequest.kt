package ru.iquack.sdk.core.auth.models

import kotlinx.serialization.Serializable


@Serializable
data class ResetPasswordWithPhoneRequest (
    val phoneNumber: String? = null
)
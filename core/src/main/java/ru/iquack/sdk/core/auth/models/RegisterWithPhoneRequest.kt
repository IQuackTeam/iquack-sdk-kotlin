package ru.iquack.sdk.core.auth.models

import kotlinx.serialization.Serializable


@Serializable
data class RegisterWithPhoneRequest (
    val phoneNumber: String? = null
)
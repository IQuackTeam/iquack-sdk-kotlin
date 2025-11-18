package ru.iquack.sdk.core.auth.models

import kotlinx.serialization.Serializable


@Serializable
data class LoginWithPhoneRequest (
    val phoneNumber: String? = null,
    val password: String? = null
)
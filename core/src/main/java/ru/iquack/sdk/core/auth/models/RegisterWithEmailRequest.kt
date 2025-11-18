package ru.iquack.sdk.core.auth.models

import kotlinx.serialization.Serializable


@Serializable
data class RegisterWithEmailRequest (
    val email: String? = null
)
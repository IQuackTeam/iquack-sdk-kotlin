package ru.iquack.sdk.core.auth.models

import kotlinx.serialization.Serializable


@Serializable
data class ChangeEmailRequest (
    val email: String? = null
)
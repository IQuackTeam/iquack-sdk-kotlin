package ru.iquack.sdk.core.auth.models

import kotlinx.serialization.Serializable


@Serializable
data class ChangePasswordRequest (
    val oldPassword: String? = null,
    val newPassword: String? = null
)
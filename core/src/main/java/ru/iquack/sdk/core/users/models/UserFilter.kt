package ru.iquack.sdk.core.users.models

import kotlinx.serialization.Serializable

@Serializable
data class UserFilter(
    val isActive: Boolean? = null,
    val isEmailVerified: Boolean? = null,
    val isPhoneVerified: Boolean? = null,
    val isDeleted: Boolean? = null
)
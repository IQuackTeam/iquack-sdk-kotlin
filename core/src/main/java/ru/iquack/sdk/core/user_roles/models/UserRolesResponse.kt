package ru.iquack.sdk.core.user_roles.models

import kotlinx.serialization.Serializable

@Serializable
data class UserRolesResponse(
    val roles: List<UserRole>
)

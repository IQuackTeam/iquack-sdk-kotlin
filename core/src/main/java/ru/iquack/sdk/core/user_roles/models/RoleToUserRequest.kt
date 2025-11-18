package ru.iquack.sdk.core.user_roles.models

import kotlinx.serialization.Serializable

@Serializable
data class RoleToUserRequest(
    val userId: String,
    val roleId: Int
)

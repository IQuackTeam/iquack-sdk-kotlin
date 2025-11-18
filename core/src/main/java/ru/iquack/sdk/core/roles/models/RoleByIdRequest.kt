package ru.iquack.sdk.core.roles.models

import kotlinx.serialization.Serializable

@Serializable
data class RoleByIdRequest(
    val id: Int
)

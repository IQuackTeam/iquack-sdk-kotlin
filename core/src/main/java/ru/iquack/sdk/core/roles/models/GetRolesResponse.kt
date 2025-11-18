package ru.iquack.sdk.core.roles.models

import kotlinx.serialization.Serializable

@Serializable
data class GetRolesResponse(
    val roles: List<Role>
)

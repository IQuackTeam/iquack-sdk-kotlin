package ru.iquack.sdk.core.roles.models

import kotlinx.serialization.Serializable

@Serializable
data class RoleByNameRequest(
    val name: String
)
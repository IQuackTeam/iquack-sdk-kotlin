package ru.iquack.sdk.core.roles.models

import kotlinx.serialization.Serializable

@Serializable
data class AddRoleRequest(
    val name: String,
    val description: String? = null
)

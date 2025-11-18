package ru.iquack.sdk.core.roles.models

import kotlinx.serialization.Serializable

@Serializable
data class UpdateDescriptionByIdRequest(
    val id: Int,
    val description: String? = null
)

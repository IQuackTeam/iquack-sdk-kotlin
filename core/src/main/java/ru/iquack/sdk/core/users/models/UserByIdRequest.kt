package ru.iquack.sdk.core.users.models

import kotlinx.serialization.Serializable

@Serializable
data class UserByIdRequest(
    val id: String
)

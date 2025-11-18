package ru.iquack.sdk.core.users.models

import kotlinx.serialization.Serializable

@Serializable
data class GetUserListResponse(
    val users: List<User>,
    val totalCount: Int
)

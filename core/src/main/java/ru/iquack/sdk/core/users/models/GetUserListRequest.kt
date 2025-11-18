package ru.iquack.sdk.core.users.models

import kotlinx.serialization.Serializable

@Serializable
data class GetUserListRequest(
    val page: Int,
    val pageSize: Int,
    val filter: UserFilter? = null
)



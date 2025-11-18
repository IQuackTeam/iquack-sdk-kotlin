package ru.iquack.sdk.core.user_roles.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class UserRole(
    val userId: String,
    val roleId: Int,
    @SerialName("assignedAt") val assignedAtRaw: String,
    val roleName: String,
    val roleDescription: String
){
    val assignedAt: Instant
        get() = Instant.parse(assignedAtRaw)
}

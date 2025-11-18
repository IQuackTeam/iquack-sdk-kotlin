package ru.iquack.sdk.core.users.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class User(
    val id: String,
    val email: String? = null,
    val phone: String? = null,
    val isActive: Boolean,
    val isEmailVerified: Boolean,
    val isPhoneVerified: Boolean,
    @SerialName("createdAt") val createdAtRaw: String,
    @SerialName("updatedAt") val updatedAtRaw: String,
    @SerialName("deletedAt") val deletedAtRaw: String? = null
){
    val createdAt: Instant
        get() = Instant.parse(createdAtRaw)

    val updatedAt: Instant
        get() = Instant.parse(updatedAtRaw)

    val deletedAt: Instant?
        get() = deletedAtRaw?.let { Instant.parse(it) }
}
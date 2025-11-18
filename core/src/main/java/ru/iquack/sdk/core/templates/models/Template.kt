package ru.iquack.sdk.core.templates.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.iquack.sdk.core.notifications.models.NotificationChannel
import java.time.Instant

@Serializable
data class Template(
    val id: String,
    val name: String,
    val channel: NotificationChannel,
    val title: String? = null,
    val content: String,
    val lang: String,
    val version: Int,
    val isActive: Boolean,
    @SerialName("createdAt") val createdAtRaw: String,
    @SerialName("updatedAt")val updatedAtRaw: String
){
    val createdAt: Instant
        get() = Instant.parse(createdAtRaw)

    val updatedAt: Instant
        get() = Instant.parse(updatedAtRaw)
}

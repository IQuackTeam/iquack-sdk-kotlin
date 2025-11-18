package ru.iquack.sdk.core.templates.models

import kotlinx.serialization.Serializable
import ru.iquack.sdk.core.notifications.models.NotificationChannel

@Serializable
data class GetActiveTemplateRequest(
    val name: String,
    val channel: NotificationChannel,
    val lang: String
)

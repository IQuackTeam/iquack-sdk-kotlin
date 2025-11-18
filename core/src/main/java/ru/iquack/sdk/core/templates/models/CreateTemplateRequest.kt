package ru.iquack.sdk.core.templates.models

import kotlinx.serialization.Serializable
import ru.iquack.sdk.core.notifications.models.NotificationChannel

@Serializable
data class CreateTemplateRequest(
    val name: String,
    val channel: NotificationChannel,
    val title: String? = null,
    val content: String,
    val lang: String? = null,
    val isActive: Boolean? = null
)

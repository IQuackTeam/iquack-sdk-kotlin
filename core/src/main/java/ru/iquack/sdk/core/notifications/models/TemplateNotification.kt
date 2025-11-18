package ru.iquack.sdk.core.notifications.models

import kotlinx.serialization.Serializable

@Serializable
data class TemplateNotification(
    val templateName: String,
    val data: Map<String, String> = emptyMap()
)

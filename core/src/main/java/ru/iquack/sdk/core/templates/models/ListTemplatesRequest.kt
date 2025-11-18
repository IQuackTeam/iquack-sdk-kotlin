package ru.iquack.sdk.core.templates.models

import kotlinx.serialization.Serializable
import ru.iquack.sdk.core.notifications.models.NotificationChannel

@Serializable
data class ListTemplatesRequest(
    val page: Int,
    val limit: Int,
    val active: Boolean? = null,
    val lang: String? = null,
    val channel: NotificationChannel? = null,
    val name: String? = null
)

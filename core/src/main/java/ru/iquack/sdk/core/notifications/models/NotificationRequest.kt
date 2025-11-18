package ru.iquack.sdk.core.notifications.models

import kotlinx.serialization.Serializable

@Serializable
data class NotificationRequest(
    val userId: String? = null,
    val channel: NotificationChannel,
    val idempotencyKey: String,
    val source: String,
    val eventId: String,
    val body: String? = null,
    val template: TemplateNotification? = null,
    val pushPayload: PushPayload? = null,
    val emailPayload: EmailPayload? = null,
    val smsPayload: SmsPayload? = null,
)

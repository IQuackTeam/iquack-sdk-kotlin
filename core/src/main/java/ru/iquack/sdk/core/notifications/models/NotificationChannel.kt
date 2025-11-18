package ru.iquack.sdk.core.notifications.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class NotificationChannel {
    @SerialName("UNSPECIFIED") UNSPECIFIED,
    @SerialName("EMAIL") EMAIL,
    @SerialName("PUSH") PUSH,
    @SerialName("SMS") SMS,
}
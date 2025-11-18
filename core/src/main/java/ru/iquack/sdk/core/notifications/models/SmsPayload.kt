package ru.iquack.sdk.core.notifications.models

import kotlinx.serialization.Serializable

@Serializable
data class SmsPayload(
    val phoneNumber: String? = null
)

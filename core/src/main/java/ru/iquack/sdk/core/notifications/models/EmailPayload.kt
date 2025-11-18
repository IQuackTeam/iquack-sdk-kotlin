package ru.iquack.sdk.core.notifications.models

import kotlinx.serialization.Serializable

@Serializable
data class EmailPayload(
    val subject: String,
    val to: String? = null,
    val from: String? = null,
)

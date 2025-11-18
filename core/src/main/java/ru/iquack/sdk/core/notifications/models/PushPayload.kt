package ru.iquack.sdk.core.notifications.models

import kotlinx.serialization.Serializable

@Serializable
data class PushPayload(
    val deviceId: String? = null,
    val title: String,
    val appId: String,
    val data: Map<String, String> = emptyMap(),
    val ttlSeconds: Int = 0
)

package ru.iquack.sdk.core.notifications.models

import kotlinx.serialization.Serializable

@Serializable
data class DeactivateDeviceRequest(
    val deviceId: String
)

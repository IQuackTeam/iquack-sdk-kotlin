package ru.iquack.sdk.core.notifications.models

import kotlinx.serialization.Serializable

@Serializable
data class RegisterDeviceRequest(
    val fcmToken: String,
    val deviceId: String,
    val deviceInfo: String? = null,
    val appInfo: AppInfo? = null
)

package ru.iquack.sdk.core.notifications.models

import kotlinx.serialization.Serializable

@Serializable
data class AppInfo(
    val appId: String,
    val appVersion: String
)

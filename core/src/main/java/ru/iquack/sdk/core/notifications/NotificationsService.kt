package ru.iquack.sdk.core.notifications

import kotlinx.serialization.json.Json
import ru.iquack.sdk.core.caller.Caller
import ru.iquack.sdk.core.notifications.models.DeactivateDeviceRequest
import ru.iquack.sdk.core.notifications.models.NotificationRequest
import ru.iquack.sdk.core.notifications.models.RegisterDeviceRequest

class NotificationsService internal constructor(
    private val caller: Caller,
    private val json: Json,
    baseUrl: String,
){

    private val baseNotificationsUrl = "$baseUrl/notifications"

    suspend fun sendNotification(req: NotificationRequest): Result<Unit> =
        caller.post("$baseNotificationsUrl/send", req) {}

    suspend fun registerDevice(req: RegisterDeviceRequest): Result<Unit> =
        caller.post("$baseNotificationsUrl/devices/register", req) {}

    suspend fun deactivateDevice(req: DeactivateDeviceRequest): Result<Unit> =
        caller.post("$baseNotificationsUrl/devices/deactivate", req) {}

}
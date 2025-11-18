package ru.iquack.sdk.core.users.models

import kotlinx.serialization.Serializable

@Serializable
data class PhoneRequest(
    val phone: String
)

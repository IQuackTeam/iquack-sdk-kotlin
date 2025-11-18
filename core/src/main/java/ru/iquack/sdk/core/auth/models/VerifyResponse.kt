package ru.iquack.sdk.core.auth.models

import kotlinx.serialization.Serializable


@Serializable
data class VerifyResponse (
    val verificationToken: String? = null
)
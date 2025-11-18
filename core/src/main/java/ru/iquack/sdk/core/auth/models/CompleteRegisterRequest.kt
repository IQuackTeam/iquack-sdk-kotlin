package ru.iquack.sdk.core.auth.models

import kotlinx.serialization.Serializable

@Serializable
data class CompleteRegisterRequest(
    val verificationToken: String? = null,
    val password: String? = null,
    val code: String? = null,
)

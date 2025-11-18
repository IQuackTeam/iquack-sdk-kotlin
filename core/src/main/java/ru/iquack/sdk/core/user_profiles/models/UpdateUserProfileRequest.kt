package ru.iquack.sdk.core.user_profiles.models

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserProfileRequest(
    val firstName: String? = null,
    val lastName: String? = null,
    val middleName: String? = null,
    val username: String? = null
)
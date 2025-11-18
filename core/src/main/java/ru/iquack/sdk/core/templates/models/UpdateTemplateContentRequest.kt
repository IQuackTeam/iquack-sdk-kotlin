package ru.iquack.sdk.core.templates.models

import kotlinx.serialization.Serializable

@Serializable
data class UpdateTemplateContentRequest(
    val id: String,
    val content: String
)

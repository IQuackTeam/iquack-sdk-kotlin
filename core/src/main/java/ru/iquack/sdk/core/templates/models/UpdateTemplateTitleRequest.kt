package ru.iquack.sdk.core.templates.models

import kotlinx.serialization.Serializable

@Serializable
data class UpdateTemplateTitleRequest(
    val id: String,
    val title: String
)

package ru.iquack.sdk.core.templates.models

import kotlinx.serialization.Serializable

@Serializable
data class ListTemplatesResponse(
    val templates: List<Template>,
    val total: Int
)

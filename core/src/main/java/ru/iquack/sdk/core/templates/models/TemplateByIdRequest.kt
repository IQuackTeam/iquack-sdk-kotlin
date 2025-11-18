package ru.iquack.sdk.core.templates.models

import kotlinx.serialization.Serializable

@Serializable
data class TemplateByIdRequest(
    val id: String
)

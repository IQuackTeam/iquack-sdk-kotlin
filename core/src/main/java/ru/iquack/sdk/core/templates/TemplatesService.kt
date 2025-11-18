package ru.iquack.sdk.core.templates

import kotlinx.serialization.json.Json
import ru.iquack.sdk.core.caller.Caller
import ru.iquack.sdk.core.templates.models.CreateTemplateRequest
import ru.iquack.sdk.core.templates.models.GetActiveTemplateRequest
import ru.iquack.sdk.core.templates.models.ListTemplatesRequest
import ru.iquack.sdk.core.templates.models.ListTemplatesResponse
import ru.iquack.sdk.core.templates.models.Template
import ru.iquack.sdk.core.templates.models.TemplateByIdRequest
import ru.iquack.sdk.core.templates.models.UpdateTemplateContentRequest
import ru.iquack.sdk.core.templates.models.UpdateTemplateTitleRequest

class TemplatesService internal constructor(
    private val caller: Caller,
    private val json: Json,
    baseUrl: String,
){

    private val baseTemplatesUrl = "$baseUrl/templates"

    suspend fun createTemplate(req: CreateTemplateRequest): Result<Template> =
        caller.post("$baseTemplatesUrl/create", req) {
            json.decodeFromString(Template.serializer(), it)
        }

    suspend fun cloneTemplate(req: TemplateByIdRequest): Result<Template> =
        caller.post("$baseTemplatesUrl/clone") {
            json.decodeFromString(Template.serializer(), it)
        }

    suspend fun getTemplateById(req: TemplateByIdRequest): Result<Template> =
        caller.get("$baseTemplatesUrl/${req.id}") {
            json.decodeFromString(Template.serializer(), it)
        }

    suspend fun getActiveTemplate(req: GetActiveTemplateRequest): Result<Template> =
        caller.post("$baseTemplatesUrl/active", req) {
            json.decodeFromString(Template.serializer(), it)
        }

    suspend fun updateTemplateTitle(req: UpdateTemplateTitleRequest): Result<Template> =
        caller.patch("$baseTemplatesUrl/title", req) {
            json.decodeFromString(Template.serializer(), it)
        }

    suspend fun updateTemplateContent(req: UpdateTemplateContentRequest): Result<Template> =
        caller.patch("$baseTemplatesUrl/content", req) {
            json.decodeFromString(Template.serializer(), it)
        }

    suspend fun setActiveTemplate(req: TemplateByIdRequest): Result<Template> =
        caller.post("$baseTemplatesUrl/activate") {
            json.decodeFromString(Template.serializer(), it)
        }

    suspend fun deactivateTemplate(req: TemplateByIdRequest): Result<Template> =
        caller.post("$baseTemplatesUrl/deactivate") {
            json.decodeFromString(Template.serializer(), it)
        }

    suspend fun listTemplates(req: ListTemplatesRequest): Result<ListTemplatesResponse> =
        caller.post("$baseTemplatesUrl/list", req) {
            json.decodeFromString(ListTemplatesResponse.serializer(), it)
        }

    suspend fun deleteTemplate(req: TemplateByIdRequest): Result<Unit> =
        caller.delete("$baseTemplatesUrl/${req.id}") {}

}
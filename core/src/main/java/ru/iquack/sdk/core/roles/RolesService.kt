package ru.iquack.sdk.core.roles

import kotlinx.serialization.json.Json
import ru.iquack.sdk.core.caller.Caller
import ru.iquack.sdk.core.roles.models.AddRoleRequest
import ru.iquack.sdk.core.roles.models.GetRolesResponse
import ru.iquack.sdk.core.roles.models.Role
import ru.iquack.sdk.core.roles.models.RoleByIdRequest
import ru.iquack.sdk.core.roles.models.RoleByNameRequest
import ru.iquack.sdk.core.roles.models.UpdateDescriptionByIdRequest
import ru.iquack.sdk.core.roles.models.UpdateDescriptionByNameRequest

class RolesService internal constructor(
    private val caller: Caller,
    private val json: Json,
    baseUrl: String,
){
    private val baseRolesUrl = "$baseUrl/roles"

    suspend fun getRoles(): Result<GetRolesResponse> =
        caller.get("$baseRolesUrl/list") {
            json.decodeFromString(GetRolesResponse.serializer(), it)
        }

    suspend fun getRoleById(req: RoleByIdRequest): Result<Role> =
        caller.get("$baseRolesUrl/${req.id}") {
            json.decodeFromString(Role.serializer(), it)
        }

    suspend fun getRoleByName(req: RoleByNameRequest): Result<Role> =
        caller.get("$baseRolesUrl/by-name/${req.name}") {
            json.decodeFromString(Role.serializer(), it)
        }

    suspend fun getDefaultRole(): Result<Role> =
        caller.get("$baseRolesUrl/default") {
            json.decodeFromString(Role.serializer(), it)
        }

    suspend fun getAdminRole(): Result<Role> =
        caller.get("$baseRolesUrl/admin") {
            json.decodeFromString(Role.serializer(), it)
        }

    suspend fun addRole(req: AddRoleRequest): Result<Role> =
        caller.post("$baseRolesUrl/add", req) {
            json.decodeFromString(Role.serializer(), it)
        }

    suspend fun updateDescriptionById(req: UpdateDescriptionByIdRequest): Result<Role> =
        caller.put("$baseRolesUrl/${req.id}/description", req) {
            json.decodeFromString(Role.serializer(), it)
        }

    suspend fun updateDescriptionByName(req: UpdateDescriptionByNameRequest): Result<Role> =
        caller.put("$baseRolesUrl/by-name/${req.name}/description", req) {
            json.decodeFromString(Role.serializer(), it)
        }

    suspend fun deleteRoleById(req: RoleByIdRequest): Result<Unit> =
        caller.delete("$baseRolesUrl/${req.id}") {}

    suspend fun deleteRoleByName(req: RoleByNameRequest): Result<Unit> =
        caller.delete("$baseRolesUrl/by-name/${req.name}") {}


}
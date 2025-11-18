package ru.iquack.sdk.core.user_roles

import kotlinx.serialization.json.Json
import ru.iquack.sdk.core.caller.Caller
import ru.iquack.sdk.core.user_roles.models.GetUserRolesRequest
import ru.iquack.sdk.core.user_roles.models.RoleToUserRequest
import ru.iquack.sdk.core.user_roles.models.UserRolesResponse

class UserRolesService internal constructor(
    private val caller: Caller,
    private val json: Json,
    baseUrl: String,
){

    private val baseUserRolesUrl = "$baseUrl/user/roles"

    suspend fun getUserRoles(): Result<UserRolesResponse> =
        caller.get(baseUserRolesUrl){
            json.decodeFromString(UserRolesResponse.serializer(), it)
        }

    suspend fun getUserRoles(req: GetUserRolesRequest): Result<UserRolesResponse> =
        caller.get("$baseUserRolesUrl/${req.userId}"){
            json.decodeFromString(UserRolesResponse.serializer(), it)
        }

    suspend fun assignRoleToUser(req: RoleToUserRequest): Result<UserRolesResponse> =
        caller.post("$baseUserRolesUrl/assign", req) {
            json.decodeFromString(UserRolesResponse.serializer(), it)
        }

    suspend fun removeRoleFromUser(req: RoleToUserRequest): Result<UserRolesResponse> =
        caller.post("$baseUserRolesUrl/remove", req) {
            json.decodeFromString(UserRolesResponse.serializer(), it)
        }


}
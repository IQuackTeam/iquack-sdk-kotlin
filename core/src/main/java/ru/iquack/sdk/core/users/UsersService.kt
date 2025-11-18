package ru.iquack.sdk.core.users

import kotlinx.serialization.json.Json
import ru.iquack.sdk.core.caller.Caller
import ru.iquack.sdk.core.users.models.EmailRequest
import ru.iquack.sdk.core.users.models.GetUserListRequest
import ru.iquack.sdk.core.users.models.GetUserListResponse
import ru.iquack.sdk.core.users.models.PhoneRequest
import ru.iquack.sdk.core.users.models.User
import ru.iquack.sdk.core.users.models.UserByIdRequest


class UsersService internal constructor(
    private val caller: Caller,
    private val json: Json,
    baseUrl: String,
){

    private val baseUsersUrl = "$baseUrl/users"

    suspend fun getUser(): Result<User> =
        caller.get("$baseUsersUrl/me"){
            json.decodeFromString(User.serializer(), it)
        }

    suspend fun getUser(req: UserByIdRequest): Result<User> =
        caller.get("$baseUsersUrl/${req.id}") {
            json.decodeFromString(User.serializer(), it)
        }

    suspend fun getUserByEmail(req: EmailRequest): Result<User> =
        caller.get("$baseUsersUrl/by_email/${req.email}") {
            json.decodeFromString(User.serializer(), it)
        }

    suspend fun getUserByPhone(req: PhoneRequest): Result<User> =
        caller.get("$baseUsersUrl/by_phone/${req.phone}") {
            json.decodeFromString(User.serializer(), it)
        }

    suspend fun getUserList(req: GetUserListRequest): Result<GetUserListResponse> =
        caller.post(baseUsersUrl, req) {
            json.decodeFromString(GetUserListResponse.serializer(), it)
        }

    suspend fun deleteUser(): Result<Unit> =
        caller.delete("$baseUsersUrl/me") {}


    suspend fun deleteUser(req: UserByIdRequest): Result<Unit> =
        caller.delete("$baseUsersUrl/${req.id}") {}


    suspend fun restoreUser(): Result<User> =
        caller.post("$baseUsersUrl/me/restore") {
            json.decodeFromString(User.serializer(), it)
        }

    suspend fun restoreUser(req: UserByIdRequest): Result<User> =
        caller.post("$baseUsersUrl/${req.id}/restore") {
            json.decodeFromString(User.serializer(), it)
        }

}
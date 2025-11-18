package ru.iquack.sdk.core.user_profiles

import kotlinx.serialization.json.Json
import ru.iquack.sdk.core.caller.Caller
import ru.iquack.sdk.core.user_profiles.models.UpdateUserProfileRequest
import ru.iquack.sdk.core.user_profiles.models.UserProfile
import ru.iquack.sdk.core.users.models.UserByIdRequest

class UserProfilesService internal constructor(
    private val caller: Caller,
    private val json: Json,
    baseUrl: String,
){
    private val baseUserProfilesUrl = "$baseUrl/user/profile"


    // The current user's profile is returned
    suspend fun getUserProfile(): Result<UserProfile> =
        caller.get("$baseUserProfilesUrl/me"){
            json.decodeFromString(UserProfile.serializer(), it)
        }

    // Returns a user's profile by their ID
    // Requires an administrator role or system key to get another user's profile
    suspend fun getUserProfile(req: UserByIdRequest): Result<UserProfile> =
        caller.get("$baseUserProfilesUrl/${req.id}"){
            json.decodeFromString(UserProfile.serializer(), it)
        }

    // Updates the current user's profile
    suspend fun updateUserProfile(req: UpdateUserProfileRequest): Result<UserProfile> =
        caller.put(baseUserProfilesUrl, req) {
            json.decodeFromString(UserProfile.serializer(), it)
        }

}
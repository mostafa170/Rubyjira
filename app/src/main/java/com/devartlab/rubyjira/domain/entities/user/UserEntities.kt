package com.devartlab.rubyjira.domain.entities.user

import com.devartlab.rubyjira.data.utils.SharedPreferencesUtils
import org.json.JSONObject

data class UserEntities(
    val id: Int,
    val avatarUrl: String,
    val name: String,
    val email: String,
    val token: String
) {

    fun toJsonString(): String =
        "{\"id\":$id,\"name\":\"$name\",\"avatarUrl\":\"$avatarUrl\"," +
                "\"email\":\"$email\",\"token\":$token}"

    fun saveToSharedPreferences() {
        SharedPreferencesUtils.saveString("_user", toJsonString())
    }
    companion object {
        private const val defaultProfile = "{\"id\":0,\"name\": \"\",\"avatarUrl\":\"\"," +
                "\"email\":\"\",\"token\": \"\"}"
        fun getSavedProfile(): UserEntities =
            JSONObject(SharedPreferencesUtils.getString("_user", defaultProfile)).run {
                UserEntities(
                    this.getInt("id"),
                    this.getString("name"),
                    this.getString("avatarUrl"),
                    this.getString("email"),
                    this.getString("token")
                )
            }
    }
}
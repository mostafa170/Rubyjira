package com.devartlab.rubyjira.data.models

import com.devartlab.rubyjira.data.utils.SharedPreferencesData
import com.devartlab.rubyjira.domain.entities.user.UserEntities
import com.google.gson.annotations.SerializedName

data class UpdateProfileResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("user")
	val user: UserUpdate
)

data class UserUpdate(

	@field:SerializedName("tenant_id")
	val tenantId: Int,

	@field:SerializedName("role")
	val role: Int,

	@field:SerializedName("tenant_owner")
	val tenantOwner: Int,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("email_verified_at")
	val emailVerifiedAt: Any,

	@field:SerializedName("media")
	val media: List<Any>,

	@field:SerializedName("uuid")
	val uuid: String,

	@field:SerializedName("deleted_at")
	val deletedAt: Any,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("email")
	val email: String
)
fun UserUpdate.toDomainUserUpdate() =
	UserEntities(id,avatarUrl,name,email, SharedPreferencesData.getAuthToken())
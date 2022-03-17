package com.devartlab.rubyjira.data.models

import com.devartlab.rubyjira.domain.entities.user.UserEntities
import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: LoginData,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("error")
	val error: Error
)

data class LoginData(

	@field:SerializedName("user")
	val user: User,

	@field:SerializedName("token")
	val token: String
)

data class Error(

	@field:SerializedName("password")
	val password: List<String>
)

data class User(

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
fun LoginData.toDomainUser() =
	UserEntities(user.id,user.avatarUrl,user.name,user.email,token)
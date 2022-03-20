package com.devartlab.rubyjira.data.models

import com.google.gson.annotations.SerializedName

data class LogoutResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String
)

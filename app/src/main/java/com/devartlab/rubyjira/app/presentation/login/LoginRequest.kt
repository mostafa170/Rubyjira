package com.devartlab.rubyjira.app.presentation.login

import com.google.gson.annotations.SerializedName

 class LoginRequest{

	@field:SerializedName("password")
	var password: String= ""

	@field:SerializedName("email")
	var email: String= ""
	override fun toString(): String = "$email | $password"
 }

package com.devartlab.rubyjira.app.presentation.changePassword

import com.google.gson.annotations.SerializedName

class ChangePasswordRequest {

    @field:SerializedName("password")
    var password: String = ""

    @field:SerializedName("password_confirmation")
    var passwordConfirmation: String = ""
    override fun toString(): String = "$password | $passwordConfirmation"
}

package com.devartlab.rubyjira.app.presentation.editProfile

class UpdateUserRequest {
    var name: String = ""
    var email: String = ""

    override fun toString(): String = "$name | $email "
}
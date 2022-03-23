package com.devartlab.rubyjira.data.datasource.changePassword

import arrow.core.Either
import com.devartlab.rubyjira.app.presentation.changePassword.ChangePasswordRequest
import com.devartlab.rubyjira.data.models.DefaultResponse
import com.devartlab.rubyjira.data.utils.AppFailure

interface ChangePasswordDataSource {
    suspend fun getChangePassword(changePasswordRequest: ChangePasswordRequest):Either<AppFailure,DefaultResponse>
}
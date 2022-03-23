package com.devartlab.rubyjira.data.repositories

import arrow.core.Either
import com.devartlab.rubyjira.app.presentation.changePassword.ChangePasswordRequest
import com.devartlab.rubyjira.data.datasource.changePassword.ChangePasswordDataSource
import com.devartlab.rubyjira.data.models.DefaultResponse
import com.devartlab.rubyjira.data.utils.AppFailure
import com.devartlab.rubyjira.domain.repositories.ChangePasswordRepository
import javax.inject.Inject

class ChangePasswordRepositoryImpl @Inject constructor(private val changePasswordDataSource: ChangePasswordDataSource) :
    ChangePasswordRepository {
    override suspend fun getChangePassword(changePasswordRequest: ChangePasswordRequest): Either<AppFailure, DefaultResponse> =
        changePasswordDataSource.getChangePassword(changePasswordRequest)

}
package com.devartlab.rubyjira.data.datasource.login

import arrow.core.Either
import com.devartlab.rubyjira.app.presentation.login.LoginRequest
import com.devartlab.rubyjira.data.utils.AppFailure
import com.devartlab.rubyjira.domain.entities.user.UserEntities

interface LoginDataSource {
    suspend fun getLogin(loginRequest: LoginRequest): Either<AppFailure, UserEntities>
}
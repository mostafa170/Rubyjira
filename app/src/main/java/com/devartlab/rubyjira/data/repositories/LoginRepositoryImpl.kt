package com.devartlab.rubyjira.data.repositories

import arrow.core.Either
import com.devartlab.rubyjira.app.presentation.login.LoginRequest
import com.devartlab.rubyjira.data.datasource.login.LoginDataSource
import com.devartlab.rubyjira.data.utils.AppFailure
import com.devartlab.rubyjira.domain.entities.user.UserEntities
import com.devartlab.rubyjira.domain.repositories.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val loginDataSource: LoginDataSource):
    LoginRepository {

    override suspend fun getLogin(
        loginRequest: LoginRequest
    ): Either<AppFailure, UserEntities> =
        loginDataSource.getLogin(loginRequest)
}
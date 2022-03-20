package com.devartlab.rubyjira.data.repositories

import arrow.core.Either
import com.devartlab.rubyjira.data.datasource.logout.LogoutDataSource
import com.devartlab.rubyjira.data.models.LogoutResponse
import com.devartlab.rubyjira.data.utils.AppFailure
import com.devartlab.rubyjira.domain.repositories.LogoutResponsitory
import javax.inject.Inject

class LogoutRepositoryImpl @Inject constructor(private val logoutDataSource: LogoutDataSource):LogoutResponsitory{
    override suspend fun getLogout(): Either<AppFailure, LogoutResponse> =logoutDataSource.getLogout()
}
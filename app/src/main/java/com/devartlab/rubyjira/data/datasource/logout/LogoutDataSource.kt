package com.devartlab.rubyjira.data.datasource.logout

import arrow.core.Either
import com.devartlab.rubyjira.data.models.LogoutResponse
import com.devartlab.rubyjira.data.utils.AppFailure

interface LogoutDataSource {
    suspend fun getLogout():Either<AppFailure,LogoutResponse>
}
package com.devartlab.rubyjira.data.datasource.logout

import arrow.core.Either
import com.devartlab.rubyjira.data.models.DefaultResponse
import com.devartlab.rubyjira.data.utils.AppFailure

interface LogoutDataSource {
    suspend fun getLogout():Either<AppFailure,DefaultResponse>
}
package com.devartlab.rubyjira.domain.repositories

import arrow.core.Either
import com.devartlab.rubyjira.data.models.DefaultResponse
import com.devartlab.rubyjira.data.utils.AppFailure

interface LogoutResponsitory {
    suspend fun getLogout():Either<AppFailure,DefaultResponse>
}
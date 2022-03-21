package com.devartlab.rubyjira.data.datasource.completeTask

import arrow.core.Either
import com.devartlab.rubyjira.data.models.DefaultResponse
import com.devartlab.rubyjira.data.utils.AppFailure

interface CompleteTaskDataSource {
    suspend fun getCompleteTask(uuid: String): Either<AppFailure, DefaultResponse>
}
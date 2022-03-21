package com.devartlab.rubyjira.data.repositories

import arrow.core.Either
import com.devartlab.rubyjira.data.datasource.completeTask.CompleteTaskDataSource
import com.devartlab.rubyjira.data.models.DefaultResponse
import com.devartlab.rubyjira.data.utils.AppFailure
import com.devartlab.rubyjira.domain.repositories.CompleteTaskRepository
import javax.inject.Inject

class CompleteTaskRepositoryImpl @Inject constructor(private val completeTaskDataSource: CompleteTaskDataSource):
    CompleteTaskRepository {
    override suspend fun getCompleteTask(uuid: String): Either<AppFailure, DefaultResponse> =completeTaskDataSource.getCompleteTask(uuid)
}
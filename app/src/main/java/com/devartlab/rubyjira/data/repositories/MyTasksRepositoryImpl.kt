package com.devartlab.rubyjira.data.repositories

import arrow.core.Either
import com.devartlab.rubyjira.data.datasource.myTasks.MyTasksDataSource
import com.devartlab.rubyjira.data.utils.AppFailure
import com.devartlab.rubyjira.domain.entities.tasks.MytasksEntities
import com.devartlab.rubyjira.domain.repositories.MyTasksRepository
import javax.inject.Inject

class MyTasksRepositoryImpl @Inject constructor(private val myTasksDataSource: MyTasksDataSource):MyTasksRepository {
    override suspend fun getMyTasks(
        project: String,
        limit: String,
        filter: String
    ): Either<AppFailure, MytasksEntities> =myTasksDataSource.getMyTasks(project,limit,filter)
}
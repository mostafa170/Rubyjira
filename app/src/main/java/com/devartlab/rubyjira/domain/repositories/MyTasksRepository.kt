package com.devartlab.rubyjira.domain.repositories

import arrow.core.Either
import com.devartlab.rubyjira.data.utils.AppFailure
import com.devartlab.rubyjira.domain.entities.tasks.MytasksEntities

interface MyTasksRepository {
    suspend fun getMyTasks(
        project: String,
        limit: String,
        filter: String
    ): Either<AppFailure,MytasksEntities>
}
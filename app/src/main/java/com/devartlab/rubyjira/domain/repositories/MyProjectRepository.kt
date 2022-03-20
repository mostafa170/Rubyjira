package com.devartlab.rubyjira.domain.repositories

import arrow.core.Either
import com.devartlab.rubyjira.data.utils.AppFailure
import com.devartlab.rubyjira.domain.entities.project.MyProjectEntities

interface MyProjectRepository {
    suspend fun getMyProject():Either<AppFailure,List<MyProjectEntities>>
}
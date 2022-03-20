package com.devartlab.rubyjira.data.datasource.myProject

import arrow.core.Either
import com.devartlab.rubyjira.data.utils.AppFailure
import com.devartlab.rubyjira.domain.entities.project.MyProjectEntities

interface MyProjectDataSource {
    suspend fun getMyProject():Either<AppFailure,List<MyProjectEntities>>
}
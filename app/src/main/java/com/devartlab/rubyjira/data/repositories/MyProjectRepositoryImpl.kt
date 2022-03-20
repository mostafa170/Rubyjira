package com.devartlab.rubyjira.data.repositories

import arrow.core.Either
import com.devartlab.rubyjira.data.datasource.myProject.MyProjectDataSource
import com.devartlab.rubyjira.data.utils.AppFailure
import com.devartlab.rubyjira.domain.entities.project.MyProjectEntities
import com.devartlab.rubyjira.domain.repositories.MyProjectRepository
import javax.inject.Inject

class MyProjectRepositoryImpl @Inject constructor(private val myProjectDataSource: MyProjectDataSource) :
    MyProjectRepository {
    override suspend fun getMyProject(): Either<AppFailure, List<MyProjectEntities>> =
        myProjectDataSource.getMyProject()
}
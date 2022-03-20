package com.devartlab.rubyjira.domain.usecases.home

import com.devartlab.rubyjira.domain.repositories.MyTasksRepository
import javax.inject.Inject

class MyTasksUseCase @Inject constructor(private val myTasksRepository: MyTasksRepository) {
    suspend operator fun invoke(
        project: String,
        limit: String,
        filter: String
    ) = myTasksRepository.getMyTasks(project, limit, filter)
}
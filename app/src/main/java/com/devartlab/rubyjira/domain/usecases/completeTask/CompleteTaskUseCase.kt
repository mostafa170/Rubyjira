package com.devartlab.rubyjira.domain.usecases.completeTask

import com.devartlab.rubyjira.domain.repositories.CompleteTaskRepository
import com.devartlab.rubyjira.domain.repositories.LogoutResponsitory
import javax.inject.Inject

class CompleteTaskUseCase @Inject constructor(private val completeTaskRepository: CompleteTaskRepository){
    suspend operator fun invoke(uuid: String)=completeTaskRepository.getCompleteTask(uuid)
}
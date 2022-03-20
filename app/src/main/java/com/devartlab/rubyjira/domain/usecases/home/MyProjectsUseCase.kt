package com.devartlab.rubyjira.domain.usecases.home

import com.devartlab.rubyjira.domain.repositories.MyProjectRepository
import javax.inject.Inject

class MyProjectsUseCase @Inject constructor(private val myProjectRepository: MyProjectRepository){
    suspend operator fun invoke()=myProjectRepository.getMyProject()
}
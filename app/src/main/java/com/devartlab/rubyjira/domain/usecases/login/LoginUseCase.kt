package com.devartlab.rubyjira.domain.usecases.login

import com.devartlab.rubyjira.app.presentation.login.LoginRequest
import com.devartlab.rubyjira.domain.repositories.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository){
    suspend operator fun  invoke(loginRequest: LoginRequest) = loginRepository.getLogin(loginRequest)
}
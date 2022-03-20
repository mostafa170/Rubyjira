package com.devartlab.rubyjira.domain.usecases.logout

import com.devartlab.rubyjira.domain.repositories.LogoutResponsitory
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val logoutResponsitory: LogoutResponsitory){
    suspend operator fun invoke()=logoutResponsitory.getLogout()
}
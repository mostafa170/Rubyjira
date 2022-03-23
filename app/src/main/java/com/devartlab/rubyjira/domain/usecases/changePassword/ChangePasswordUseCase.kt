package com.devartlab.rubyjira.domain.usecases.changePassword

import com.devartlab.rubyjira.app.presentation.changePassword.ChangePasswordRequest
import com.devartlab.rubyjira.domain.repositories.ChangePasswordRepository
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(private val changePasswordRepository: ChangePasswordRepository) {
    suspend operator fun  invoke(changePasswordRequest: ChangePasswordRequest)=changePasswordRepository.getChangePassword(changePasswordRequest)
}
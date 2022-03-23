package com.devartlab.rubyjira.domain.usecases.updateProfileData

import com.devartlab.rubyjira.domain.repositories.UpdateProfileDataRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class UpdateProfileDataUseCase @Inject constructor(private val updateProfileDataRepository: UpdateProfileDataRepository) {
    suspend operator fun invoke(
        body: Map<String, RequestBody?>,
        profilePicture: MultipartBody.Part
    )=updateProfileDataRepository.getUpdateProfile(body, profilePicture)
}
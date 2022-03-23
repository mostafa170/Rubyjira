package com.devartlab.rubyjira.data.repositories

import arrow.core.Either
import com.devartlab.rubyjira.data.datasource.updateProfile.UpdateProfileDataSource
import com.devartlab.rubyjira.data.utils.AppFailure
import com.devartlab.rubyjira.domain.entities.user.UserEntities
import com.devartlab.rubyjira.domain.repositories.UpdateProfileDataRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class UpdateProfileDataRepositoryImpl @Inject constructor(private  val updateProfileDataSource: UpdateProfileDataSource):
    UpdateProfileDataRepository {
    override suspend fun getUpdateProfile(
        body: Map<String, RequestBody?>,
        profilePicture: MultipartBody.Part
    ): Either<AppFailure, UserEntities> =
        updateProfileDataSource.updateProfile(body,profilePicture)
}
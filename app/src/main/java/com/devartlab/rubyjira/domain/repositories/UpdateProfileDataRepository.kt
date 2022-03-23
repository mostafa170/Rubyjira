package com.devartlab.rubyjira.domain.repositories

import arrow.core.Either
import com.devartlab.rubyjira.data.utils.AppFailure
import com.devartlab.rubyjira.domain.entities.user.UserEntities
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface UpdateProfileDataRepository {
    suspend fun getUpdateProfile(
        body: Map<String, RequestBody?>,
        profilePicture: MultipartBody.Part): Either<AppFailure, UserEntities>
}
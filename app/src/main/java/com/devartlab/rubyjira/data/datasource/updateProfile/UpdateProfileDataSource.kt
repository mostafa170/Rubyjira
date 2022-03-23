package com.devartlab.rubyjira.data.datasource.updateProfile

import arrow.core.Either
import com.devartlab.rubyjira.data.utils.AppFailure
import com.devartlab.rubyjira.domain.entities.user.UserEntities
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface UpdateProfileDataSource {
    suspend fun updateProfile(body: Map<String, RequestBody?>,
                              profilePicture: MultipartBody.Part): Either<AppFailure, UserEntities>
}
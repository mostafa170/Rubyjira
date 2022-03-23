package com.devartlab.rubyjira.data.datasource.updateProfile

import arrow.core.Either
import com.devartlab.rubyjira.data.models.UpdateProfileResponse
import com.devartlab.rubyjira.data.models.toDomainUserUpdate
import com.devartlab.rubyjira.data.network.ApiManager
import com.devartlab.rubyjira.data.utils.*
import com.devartlab.rubyjira.domain.entities.user.UserEntities
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class UpdateProfileDataSourceImpl @Inject constructor(private val dispatcher: CoroutineDispatcher) :
    UpdateProfileDataSource {
    override suspend fun updateProfile(
        body: Map<String, RequestBody?>,
        profilePicture: MultipartBody.Part?
    ): Either<AppFailure, UserEntities> =
        withContext(dispatcher) {
            try {
                val response: Response<UpdateProfileResponse> =
                    ApiManager.apiCalls.getUpdateProfileApiAsync(body, profilePicture).await()
                when {
                    response.code() == SUCCESS -> {
                        response.body()?.let { body ->
                            body.user.let {
                                SharedPreferencesData.setAuthToken(SharedPreferencesData.getAuthToken())
                                it.toDomainUserUpdate().saveToSharedPreferences()
                                return@withContext Either.Right(it.toDomainUserUpdate())
                            }
                        } ?: return@withContext Either.Left(SomethingWentWrongFailure("No data!"))
                    }
                    else -> return@withContext Either.Left(
                        SomethingWentWrongFailure(
                            response.errorBody()?.getErrors()
                                ?: "${response.code()}: ${response.message()}"
                        )
                    )
                }
            } catch (e: Exception) {
                return@withContext Either.Left(
                    SomethingWentWrongFailure(
                        e.message ?: "Something went wrong!"
                    )
                )
            }
        }
}
package com.devartlab.rubyjira.data.datasource.login

import arrow.core.Either
import com.devartlab.rubyjira.app.presentation.login.LoginRequest
import com.devartlab.rubyjira.data.models.LoginResponse
import com.devartlab.rubyjira.data.models.toDomainUser
import com.devartlab.rubyjira.data.network.ApiManager
import com.devartlab.rubyjira.data.utils.*
import com.devartlab.rubyjira.domain.entities.user.UserEntities
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor( private val dispatcher: CoroutineDispatcher): LoginDataSource{

    override suspend fun getLogin(
        loginRequest: LoginRequest
    ): Either<AppFailure, UserEntities> =
        withContext(dispatcher){
            try {
                val response: Response<LoginResponse> = ApiManager.apiCalls.getLoginApiAsync(loginRequest).await()
                when {
                    response.code() == SUCCESS -> {
                        response.body()?.let { body ->
                            body.data.let {
                                SharedPreferencesData.setAuthToken(body.data.token)
                                it.toDomainUser().saveToSharedPreferences()
                                return@withContext Either.Right(it.toDomainUser())
                            }
                        } ?: return@withContext Either.Left(SomethingWentWrongFailure("No data!"))
                    }
                    else -> return@withContext Either.Left(SomethingWentWrongFailure(response.errorBody()?.getErrors() ?: "${response.code()}: ${response.message()}"))
                }
            }catch (e: Exception){
                return@withContext Either.Left(SomethingWentWrongFailure(e.message ?: "Something went wrong!"))
            }
        }
}
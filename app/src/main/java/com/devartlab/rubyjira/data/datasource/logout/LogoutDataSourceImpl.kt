package com.devartlab.rubyjira.data.datasource.logout

import arrow.core.Either
import com.devartlab.rubyjira.data.models.DefaultResponse
import com.devartlab.rubyjira.data.network.ApiManager
import com.devartlab.rubyjira.data.utils.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class LogoutDataSourceImpl @Inject constructor(private val dispatcher: CoroutineDispatcher):LogoutDataSource{
    override suspend fun getLogout():Either<AppFailure, DefaultResponse> =
        withContext(dispatcher){
            try {
                val response: Response<DefaultResponse> = ApiManager.apiCalls.getLogoutApiAsync().await()
                when {
                    response.code() == SUCCESS -> {
                        response.body()?.let { body ->
                            body.let {
                                return@withContext Either.Right(it)
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
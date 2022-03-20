package com.devartlab.rubyjira.data.datasource.myProject

import arrow.core.Either
import com.devartlab.rubyjira.data.models.MyProjectResponse
import com.devartlab.rubyjira.data.models.toMyProjectDomain
import com.devartlab.rubyjira.data.network.ApiManager
import com.devartlab.rubyjira.data.utils.AppFailure
import com.devartlab.rubyjira.data.utils.SUCCESS
import com.devartlab.rubyjira.data.utils.SomethingWentWrongFailure
import com.devartlab.rubyjira.data.utils.getErrors
import com.devartlab.rubyjira.domain.entities.project.MyProjectEntities
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class MyProjectDataSourceImpl @Inject constructor(private val dispatcher: CoroutineDispatcher):MyProjectDataSource{
    override suspend fun getMyProject(): Either<AppFailure, List<MyProjectEntities>> =
        withContext(dispatcher){
            try {
                val response: Response<MyProjectResponse> = ApiManager.apiCalls.getMyProjectsApiAsync().await()
                when {
                    response.code() == SUCCESS -> {
                        response.body()?.let { body ->
                            body.let {
                                return@withContext Either.Right(it.data.toMyProjectDomain())
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
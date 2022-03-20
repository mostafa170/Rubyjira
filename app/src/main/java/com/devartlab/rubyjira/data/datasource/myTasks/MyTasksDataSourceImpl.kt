package com.devartlab.rubyjira.data.datasource.myTasks

import arrow.core.Either
import com.devartlab.rubyjira.data.models.MyTasksResponse
import com.devartlab.rubyjira.data.models.toMyTaskDomain
import com.devartlab.rubyjira.data.network.ApiManager
import com.devartlab.rubyjira.data.utils.AppFailure
import com.devartlab.rubyjira.data.utils.SUCCESS
import com.devartlab.rubyjira.data.utils.SomethingWentWrongFailure
import com.devartlab.rubyjira.data.utils.getErrors
import com.devartlab.rubyjira.domain.entities.tasks.MytasksEntities
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class MyTasksDataSourceImpl @Inject constructor(private val dispatcher: CoroutineDispatcher):MyTasksDataSource{
    override suspend fun getMyTasks(
        project: String,
        limit: String,
        filter: String
    ): Either<AppFailure, MytasksEntities> =
        withContext(dispatcher){
            try {
                val response: Response<MyTasksResponse> = ApiManager.apiCalls.getMyTasksApiAsync(project,limit,filter).await()
                when {
                    response.code() == SUCCESS -> {
                        response.body()?.let { body ->
                            body.let {
                                return@withContext Either.Right(it.data.toMyTaskDomain())
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
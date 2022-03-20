package com.devartlab.rubyjira.data.network

import com.devartlab.rubyjira.app.presentation.login.LoginRequest
import com.devartlab.rubyjira.data.models.LoginResponse
import com.devartlab.rubyjira.data.models.LogoutResponse
import com.devartlab.rubyjira.data.models.MyProjectResponse
import com.devartlab.rubyjira.data.models.MyTasksResponse
import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface APICalls {
    // Profile
//    @POST("api/v1/get_user_profile")
//    fun getUserProfileDataAsync(): Deferred<Response<ApiResult<UserDto>>>
    @POST("login")
    fun getLoginApiAsync(@Body loginRequest: LoginRequest): Deferred<Response<LoginResponse>>

    @GET("logout")
    fun getLogoutApiAsync(): Deferred<Response<LogoutResponse>>

    @GET("my_projects")
    fun getMyProjectsApiAsync(): Deferred<Response<MyProjectResponse>>

    @GET("my_tasks")
    fun getMyTasksApiAsync(
        @Query("project") project: String,
        @Query("limit") limit: String,
        @Query("filter") filter: String
    ): Deferred<Response<MyTasksResponse>>

}
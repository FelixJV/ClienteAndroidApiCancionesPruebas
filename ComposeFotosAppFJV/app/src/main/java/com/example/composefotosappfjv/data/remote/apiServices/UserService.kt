package com.example.composefotosappfjv.data.remote.apiServices

import com.example.composefotosappfjv.domain.modelo.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @POST("/auth/register")
    suspend fun registerUser(@Body user: User): Response<LoginResponse>

    @GET("/auth/login")
    suspend fun loginUser(
        @Query("nombre") nombre: String,
        @Query("password") password: String
    ): Response<String>

}
data class LoginResponse(
    val accessToken: String,
    val refreshToken: String
)
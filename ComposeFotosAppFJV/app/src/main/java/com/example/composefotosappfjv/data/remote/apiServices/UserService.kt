package com.example.composefotosappfjv.data.remote.apiServices

import com.example.composefotosappfjv.domain.modelo.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @POST("/auth")
    suspend fun registerUser(@Body user: User): Response<Boolean>

    @GET("/auth")
    suspend fun loginUser(
        @Query("nombre") nombre: String,
        @Query("password") password: String
    ): Response<LoginResponse>
    @POST("/auth/refresh")
    suspend fun refreshToken(@Body refreshToken: String): Response<LoginResponse>
}
data class LoginResponse(
    val accessToken: String,
    val refreshToken: String
)
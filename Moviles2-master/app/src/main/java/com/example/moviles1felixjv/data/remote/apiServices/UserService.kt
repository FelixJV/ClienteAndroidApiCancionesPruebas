package com.example.moviles1felixjv.data.remote.apiServices

import com.example.moviles1felixjv.data.remote.modelo.Users
import com.example.moviles1felixjv.data.remote.modelo.UsersItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @POST("/auth/register")
    suspend fun registerUser(@Body user: UsersItem): Response<String>

    @GET("/auth/login")
    suspend fun loginUser(
        @Query("nombre") nombre: String,
        @Query("password") password: String
    ): Response<String>

}
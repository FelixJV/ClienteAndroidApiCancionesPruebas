package com.example.composefotosappfjv.data.remote.apiServices

import com.example.composefotosappfjv.domain.modelo.Cancion
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CancionService {
    @GET("playlist")
    suspend fun getCanciones(@Header("Authorization") token: String): Response<List<Cancion>>

    @GET("playlist/{id}")
    suspend fun getCancion(@Header("Authorization") token: String, @Path("id") id: Int): Response<Cancion>

    @POST("playlist")
    suspend fun addSong(@Header("Authorization") token: String, @Body song: Cancion): Response<String>

    @PUT("playlist/{id}")
    suspend fun updateSong(@Header("Authorization") token: String, @Path("id") id: Int, @Body song: Cancion): Response<String>

    @DELETE("playlist/{id}")
    suspend fun deleteSong(@Header("Authorization") token: String, @Path("id") id: Int): Response<String>
}

package com.example.composefotosappfjv.data.remote.dataSource

import com.example.composefotosappfjv.data.remote.NetworkResult
import com.example.composefotosappfjv.data.remote.apiServices.CancionService
import com.example.composefotosappfjv.domain.modelo.Cancion
import javax.inject.Inject

class CancionDataSource @Inject constructor(
    private val plServices: CancionService
) : BaseApiResponse() {

    suspend fun fetchCanciones(token: String): NetworkResult<List<Cancion>> =
        safeApiCall { plServices.getCanciones(token) }

    suspend fun fetchCancion(id: Int, token: String): NetworkResult<Cancion> =
        safeApiCall { plServices.getCancion(token, id) }

    suspend fun updateCancion(token: String, id: Int, cancion: Cancion): NetworkResult<String> =
        safeApiCall { plServices.updateSong(token, id, cancion) }

    suspend fun deleteCancion(token: String, id: Int): NetworkResult<String> =
        safeApiCall { plServices.deleteSong(token, id) }

    suspend fun addCancion(token: String,cancion: Cancion): NetworkResult<String> =
        safeApiCall { plServices.addSong(token, cancion) }
}

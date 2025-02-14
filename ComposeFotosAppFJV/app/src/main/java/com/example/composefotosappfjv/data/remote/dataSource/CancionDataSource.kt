package com.example.composefotosappfjv.data.remote.dataSource

import com.example.composefotosappfjv.data.remote.NetworkResult
import com.example.composefotosappfjv.data.remote.apiServices.CancionService
import com.example.composefotosappfjv.domain.modelo.Cancion
import javax.inject.Inject

class CancionDataSource @Inject constructor(
    private val plServices: CancionService
) : BaseApiResponse() {

    suspend fun fetchCanciones(): NetworkResult<List<Cancion>> =
        safeApiCall { plServices.getCanciones() }

    suspend fun fetchCancion(id: Int): NetworkResult<Cancion> =
        safeApiCall { plServices.getCancion(id) }

    suspend fun updateCancion(id: Int, cancion: Cancion): NetworkResult<String> =
        safeApiCall { plServices.updateSong(id, cancion) }

    suspend fun deleteCancion( id: Int): NetworkResult<String> =
        safeApiCall { plServices.deleteSong(id) }

    suspend fun addCancion(cancion: Cancion): NetworkResult<String> =
        safeApiCall { plServices.addSong(cancion) }
}

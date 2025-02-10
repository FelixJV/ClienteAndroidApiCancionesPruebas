package com.example.moviles1felixjv.data.remote.dataSource

import com.example.moviles1felixjv.data.remote.NetworkResult
import com.example.moviles1felixjv.data.remote.apiServices.PlaylistService
import com.example.moviles1felixjv.domain.domain.modelo.Cancion
import javax.inject.Inject

class CancionDataSource @Inject constructor(
    private val plServices: PlaylistService
) : BaseApiResponse() {

    suspend fun fetchPlaylist(token: String): NetworkResult<List<Cancion>> =
        safeApiCall { plServices.getPlaylist(token) }

    suspend fun fetchFoto(id: Int, token: String): NetworkResult<Cancion> =
        safeApiCall { plServices.getSong(token, id) }

    suspend fun updateFoto(token: String, id: Int, cancion: Cancion): NetworkResult<String> =
        safeApiCall { plServices.updateSong(token, id, cancion) }

    suspend fun deleteUser(token: String, id: Int): NetworkResult<String> =
        safeApiCall { plServices.deleteSong(token, id) }
}

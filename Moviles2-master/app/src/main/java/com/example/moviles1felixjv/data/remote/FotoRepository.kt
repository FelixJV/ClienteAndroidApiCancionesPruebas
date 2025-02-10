package com.example.moviles1felixjv.data.remote

import com.example.moviles1felixjv.data.remote.dataSource.CancionDataSource
import com.example.moviles1felixjv.domain.domain.modelo.Foto
import javax.inject.Inject

class FotoRepository @Inject constructor(
    private val fotoDataSource: CancionDataSource,
){
    suspend fun fetchFotos(): NetworkResult<List<Foto>> {

        return fotoDataSource.fetchFotos()

    }

    suspend fun fetchFoto(id: Int): NetworkResult<Foto> {

        return fotoDataSource.fetchFoto(id)

    }
    suspend fun updateFoto(id: Int, foto: Foto): NetworkResult<Foto> { return fotoDataSource.updateFoto(id, foto)
    }

    suspend fun deleteFoto(id: Int) = fotoDataSource.deleteUser(id)
}
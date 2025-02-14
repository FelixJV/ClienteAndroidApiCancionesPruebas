package com.example.composefotosappfjv.data.remote

import com.example.composefotosappfjv.data.remote.dataSource.CancionDataSource
import com.example.composefotosappfjv.data.remote.di.IoDispatcher
import com.example.composefotosappfjv.domain.modelo.Cancion
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*

class CancionRepository @Inject constructor(
    private val cancionDataSource: CancionDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend fun fetchCanciones(): Flow<NetworkResult<List<Cancion>>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = cancionDataSource.fetchCanciones()
            emit(result)
        }.catch { e ->
            emit(NetworkResult.Error(e.message ?: "Error fetching canciones"))
        }.flowOn(dispatcher)
    }

    suspend fun fetchCancion(id: Int): Flow<NetworkResult<Cancion>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = cancionDataSource.fetchCancion(id)
            emit(result)
        }.catch { e ->
            emit(NetworkResult.Error(e.message ?: "Error fetching cancion"))
        }.flowOn(dispatcher)
    }

    suspend fun updateCancion(id: Int, cancion: Cancion): Flow<NetworkResult<String>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = cancionDataSource.updateCancion(id, cancion)
            emit(result)
        }.catch { e ->
            emit(NetworkResult.Error(e.message ?: "Error updating cancion"))
        }.flowOn(dispatcher)
    }

    suspend fun deleteCancion(id: Int): Flow<NetworkResult<Unit>> {
        return flow {
            emit(NetworkResult.Loading())
            cancionDataSource.deleteCancion(id)
            emit(NetworkResult.Success(Unit))
        }.catch { e ->
            emit(NetworkResult.Error(e.message ?: "Error deleting cancion"))
        }.flowOn(dispatcher)
    }

    suspend fun addCancion(cancion: Cancion): Flow<NetworkResult<Unit>> {
        return flow {
            emit(NetworkResult.Loading())
            cancionDataSource.addCancion(cancion)
            emit(NetworkResult.Success(Unit))
        }.catch { e ->
            emit(NetworkResult.Error(e.message ?: "Error adding cancion"))
        }.flowOn(dispatcher)
    }
}

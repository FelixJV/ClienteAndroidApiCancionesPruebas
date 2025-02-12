package com.example.composefotosappfjv.data.remote

import com.example.composefotosappfjv.data.remote.dataSource.UserDataSource
import com.example.composefotosappfjv.data.remote.di.IoDispatcher
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*


class UserRepository @Inject constructor(
    private val userDataSource: UserDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend fun registerUser(name: String, pass: String): Flow<NetworkResult<String>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = userDataSource.registerUser(name, pass)
            emit(result)
        }.catch { e ->
            emit(NetworkResult.Error(e.message ?: "Error registering user"))
        }.flowOn(dispatcher)
    }

    suspend fun fetchUser(name: String, pass: String): Flow<NetworkResult<String>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = userDataSource.fetchUser(name, pass)
            emit(result)
        }.catch { e ->
            emit(NetworkResult.Error(e.message ?: "Error fetching user"))
        }.flowOn(dispatcher)
    }
}
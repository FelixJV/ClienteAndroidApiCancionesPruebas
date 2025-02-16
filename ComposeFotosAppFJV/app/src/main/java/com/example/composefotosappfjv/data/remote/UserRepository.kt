package com.example.composefotosappfjv.data.remote

import com.example.composefotosappfjv.data.remote.apiServices.LoginResponse
import com.example.composefotosappfjv.data.remote.dataSource.UserDataSource
import com.example.composefotosappfjv.data.remote.di.IoDispatcher
import com.example.composefotosappfjv.domain.modelo.User
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*


class UserRepository @Inject constructor(
    private val userDataSource: UserDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend fun registerUser(user: User): Flow<NetworkResult<Boolean>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = userDataSource.registerUser(user)
            emit(result)
        }.catch { e ->
            emit(NetworkResult.Error(e.message ?: "Error registering user"))
        }.flowOn(dispatcher)
    }

    suspend fun fetchUser(name: String, pass: String): Flow<NetworkResult<LoginResponse>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = userDataSource.fetchUser(name, pass)
            emit(result)
        }.catch { e ->
            emit(NetworkResult.Error(e.message ?: "Error fetching user"))
        }.flowOn(dispatcher)
    }
}
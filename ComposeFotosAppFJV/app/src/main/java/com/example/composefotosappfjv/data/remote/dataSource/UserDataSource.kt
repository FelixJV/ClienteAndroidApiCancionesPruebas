package com.example.composefotosappfjv.data.remote.dataSource

import com.example.composefotosappfjv.data.remote.NetworkResult
import com.example.composefotosappfjv.data.remote.apiServices.UserService
import com.example.composefotosappfjv.domain.modelo.User
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val userServices: UserService
) : BaseApiResponse() {

    suspend fun fetchUser(name:String, pass: String): NetworkResult<String> =
        safeApiCall { userServices.loginUser(name,pass) }

    suspend fun registerUser(user: User): NetworkResult<Boolean> =
        safeApiCall { userServices.registerUser(user) }

}
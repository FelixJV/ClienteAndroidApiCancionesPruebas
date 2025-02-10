package com.example.moviles1felixjv.data.remote.dataSource

import com.example.moviles1felixjv.data.remote.NetworkResult
import com.example.moviles1felixjv.data.remote.apiServices.UserService
import com.example.moviles1felixjv.data.remote.modelo.toUser
import com.example.moviles1felixjv.domain.domain.modelo.User
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val userServices: UserService
) : BaseApiResponse() {

    suspend fun fetchUser(name:String, pass: String): NetworkResult<String> =
        safeApiCall { userServices.loginUser(name,pass) }

    suspend fun registerUser(name:String, pass: String): NetworkResult<String> =
        safeApiCall { userServices.loginUser(name,pass) }

}
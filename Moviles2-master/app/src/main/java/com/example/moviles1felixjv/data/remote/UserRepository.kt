package com.example.moviles1felixjv.data.remote

import com.example.moviles1felixjv.data.remote.dataSource.UserDataSource
import com.example.moviles1felixjv.domain.domain.modelo.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDataSource: UserDataSource,
){
    suspend fun registerUser(name:String, pass: String): NetworkResult<String> {

        return userDataSource.registerUser(name,pass)

    }

    suspend fun fetchUser(name:String, pass: String): NetworkResult<String> {

        return userDataSource.fetchUser(name,pass)

    }

}
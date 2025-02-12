package com.example.composefotosappfjv.domain.usecases.userUsecase

import com.example.composefotosappfjv.data.remote.NetworkResult
import com.example.composefotosappfjv.data.remote.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RegisterUser @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(name:String,pass:String,mail:String): Flow<NetworkResult<Boolean>> = userRepository.registerUser(name, pass,mail)
}


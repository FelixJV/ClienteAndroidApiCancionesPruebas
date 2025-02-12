package com.example.composefotosappfjv.domain.usecases.userUsecase

import com.example.composefotosappfjv.data.remote.NetworkResult
import com.example.composefotosappfjv.data.remote.UserRepository
import com.example.composefotosappfjv.domain.modelo.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUser @Inject constructor(private val userRepository: UserRepository){
    suspend operator fun invoke(name: String, pass: String) : Flow<NetworkResult<String>> = userRepository.fetchUser(name, pass)
}
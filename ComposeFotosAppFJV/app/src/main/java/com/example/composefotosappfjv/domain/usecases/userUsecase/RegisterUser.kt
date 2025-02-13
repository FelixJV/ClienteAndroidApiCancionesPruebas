package com.example.composefotosappfjv.domain.usecases.userUsecase

import com.example.composefotosappfjv.data.remote.NetworkResult
import com.example.composefotosappfjv.data.remote.UserRepository
import com.example.composefotosappfjv.domain.modelo.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RegisterUser @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: User): Flow<NetworkResult<Boolean>> = userRepository.registerUser(user)
}


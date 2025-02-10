package com.example.moviles1felixjv.domain.domain.usecases.userUsecase

import com.example.moviles1felixjv.data.remote.NetworkResult
import com.example.moviles1felixjv.data.remote.UserRepository
import com.example.moviles1felixjv.domain.domain.modelo.User
import javax.inject.Inject


class GetUsers @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(): NetworkResult<List<User>> = userRepository.fetchUsers()
}


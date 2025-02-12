package com.example.composefotosappfjv.domain.usecases.cancionUsecases

import com.example.composefotosappfjv.data.remote.NetworkResult
import com.example.composefotosappfjv.data.remote.CancionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteCancion @Inject constructor(private val cancionRepository: CancionRepository) {
    suspend operator fun invoke(id: Int, token:String) : Flow<NetworkResult<Unit>> = cancionRepository.deleteCancion(id,token)}
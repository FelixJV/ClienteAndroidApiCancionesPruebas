package com.example.composefotosappfjv.domain.usecases.cancionUsecases

import com.example.composefotosappfjv.data.remote.CancionRepository
import com.example.composefotosappfjv.data.remote.NetworkResult
import com.example.composefotosappfjv.domain.modelo.Cancion
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateCancion @Inject constructor(private val cancionRepository: CancionRepository) {
    suspend operator fun invoke(id: Int, cancion: Cancion, token: String) : Flow<NetworkResult<String>> = cancionRepository.updateCancion(id,cancion, token)}
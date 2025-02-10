package com.example.moviles1felixjv.domain.domain.usecases.fotoUsecase

import com.example.moviles1felixjv.data.remote.FotoRepository
import com.example.moviles1felixjv.data.remote.NetworkResult
import com.example.moviles1felixjv.domain.domain.modelo.Foto
import javax.inject.Inject

class DeleteFoto @Inject constructor(private val fotoRepository: FotoRepository) {
    suspend operator fun invoke(id: Int) : NetworkResult<Unit> = fotoRepository.deleteFoto(id)}
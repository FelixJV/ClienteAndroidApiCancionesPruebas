package com.example.moviles1felixjv.domain.domain.usecases.fotoUsecase

import com.example.moviles1felixjv.data.remote.FotoRepository
import com.example.moviles1felixjv.data.remote.NetworkResult
import com.example.moviles1felixjv.data.remote.modelo.FotosItem
import com.example.moviles1felixjv.domain.domain.modelo.Foto
import javax.inject.Inject

class UpdateFoto @Inject constructor(private val fotoRepository: FotoRepository) {
    suspend operator fun invoke(id: Int, foto: Foto) : NetworkResult<Foto> = fotoRepository.updateFoto(id,foto)}
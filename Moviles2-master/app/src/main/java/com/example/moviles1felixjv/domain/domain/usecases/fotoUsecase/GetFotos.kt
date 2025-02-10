package com.example.moviles1felixjv.domain.domain.usecases.fotoUsecase

import com.example.moviles1felixjv.data.remote.FotoRepository
import com.example.moviles1felixjv.data.remote.NetworkResult
import com.example.moviles1felixjv.domain.domain.modelo.Foto
import javax.inject.Inject

class GetFotos @Inject constructor(private val fotosRepository: FotoRepository){
    suspend operator fun invoke() : NetworkResult<List<Foto>> = fotosRepository.fetchFotos()
}
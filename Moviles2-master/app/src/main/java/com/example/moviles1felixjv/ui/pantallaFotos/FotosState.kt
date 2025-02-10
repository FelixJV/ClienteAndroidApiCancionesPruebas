package com.example.moviles1felixjv.ui.pantallaFotos

import com.example.moviles1felixjv.domain.domain.modelo.Foto
import com.example.viewmodel.ui.common.UiEvent

data class FotosState(
    val fotos: List<Foto> = emptyList(),
    val isIrPerfil: Boolean = false,
    val event: UiEvent? = null,
    val isLoading: Boolean = false
)
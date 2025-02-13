package com.example.composefotosappfjv.ui.pantallaDetalleCancion

import com.example.composefotosappfjv.domain.modelo.Cancion
import com.example.composefotosappfjv.ui.common.UiEvent

data class DetalleCancionState (
    val cancion: Cancion ,
    val mensaje: String? = null,
    val event: UiEvent? = null,
    val isLoading: Boolean = false,
)
package com.example.composefotosappfjv.ui.pantallaCanciones

import com.example.composefotosappfjv.domain.modelo.Cancion
import com.example.composefotosappfjv.ui.common.UiEvent

data class CancionesState(
    val canciones: List<Cancion> = emptyList(),
    val isIrPerfil: Boolean = false,
    val event: UiEvent? = null,
    val isLoading: Boolean = false
)
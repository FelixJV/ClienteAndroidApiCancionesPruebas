package com.example.moviles1felixjv.ui.pantallaDetalleFoto

import com.example.moviles1felixjv.domain.domain.modelo.Foto
import com.example.viewmodel.ui.common.UiEvent

data class DetalleFotosState (
    val foto: Foto? = null,
    val mensaje: String? = null,
    val event: UiEvent? = null,
)
package com.example.moviles1felixjv.ui.pantallaDetalleFoto

import com.example.moviles1felixjv.domain.domain.modelo.Foto
import com.example.moviles1felixjv.ui.pantallaLogin.LoginEvent

sealed class DetalleFotosEvent {
    class ActualizarFoto(val foto: Foto) : DetalleFotosEvent()
    class DeleteFoto(val id: Int) : DetalleFotosEvent()
    class GetFoto(val idFoto: Int): DetalleFotosEvent()
    class errorMostrado: DetalleFotosEvent()
}
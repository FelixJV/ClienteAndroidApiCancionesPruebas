package com.example.composefotosappfjv.ui.pantallaDetalleCancion

import com.example.composefotosappfjv.domain.modelo.Cancion

sealed class DetalleCancionEvent {
    class ActualizarCancion(val cancion: Cancion) : DetalleCancionEvent()
    class DeleteCancion(val id: Int) : DetalleCancionEvent()
    class GetCancion(val id: Int): DetalleCancionEvent()
    class errorMostrado: DetalleCancionEvent()
}
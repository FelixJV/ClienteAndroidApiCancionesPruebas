package com.example.moviles1felixjv.ui.pantallaFotos

sealed class FotosEvent {
    class getFotos(val albumId: Int): FotosEvent()
}
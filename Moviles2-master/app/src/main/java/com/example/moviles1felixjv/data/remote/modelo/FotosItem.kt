package com.example.moviles1felixjv.data.remote.modelo

import com.example.moviles1felixjv.domain.domain.modelo.Cancion

data class CancionItem(
    val id: Int,
    val title: String,
    val artista: String
)
fun CancionItem.toCancion() =
    Cancion(
        id = id,
        titulo = title,
        artista = artista
    )
package com.example.composefotosappfjv.ui.navigation

import kotlinx.serialization.Serializable


sealed interface Destination {
    @Serializable
    object CancionesDestination : Destination {
        const val route = "canciones"
    }

    @Serializable
    object DetalleCancionDestination : Destination {
        const val route = "detalleCancion/{id}"

        fun createRoute(id: Int) = "detalleCancion/$id"
    }

    @Serializable
    object LoginDestination : Destination {
        const val route = "login"
    }
}


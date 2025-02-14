package com.example.composefotosappfjv.ui.pantallaCanciones

import com.example.composefotosappfjv.ui.pantallaLogin.LoginEvent

sealed class CancionesEvent {
    class getCanciones : CancionesEvent()
    object UiEventDone : CancionesEvent()
}
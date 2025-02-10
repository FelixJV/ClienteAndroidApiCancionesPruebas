package com.example.moviles1felixjv.ui.pantallaLogin

sealed class LoginEvent {
    class validateUser(val id: Int): LoginEvent()
    class errorMostrado: LoginEvent()
}
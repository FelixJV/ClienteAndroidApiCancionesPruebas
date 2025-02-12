package com.example.composefotosappfjv.ui.pantallaLogin

sealed class LoginEvent {
    class registerUser(val name: String, val pass: String, val email: String): LoginEvent()
    class loginUser(val name:String, val pass: String): LoginEvent()
    object UiEventDone : LoginEvent()
}
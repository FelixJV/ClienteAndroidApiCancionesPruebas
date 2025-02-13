package com.example.composefotosappfjv.ui.pantallaLogin

import com.example.composefotosappfjv.domain.modelo.User

sealed class LoginEvent {
    class registerUser(val user: User): LoginEvent()
    class loginUser(val name:String, val pass: String): LoginEvent()
    object UiEventDone : LoginEvent()
}
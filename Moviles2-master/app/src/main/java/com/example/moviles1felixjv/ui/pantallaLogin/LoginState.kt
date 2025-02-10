package com.example.moviles1felixjv.ui.pantallaLogin

import com.example.moviles1felixjv.domain.domain.modelo.User
import com.example.viewmodel.ui.common.UiEvent

data class LoginState(
    val user: User? = null,
    val mensaje: String? = null,
    val event: UiEvent? = null,
    val isLoading: Boolean = false,
    val userEncontrado: Boolean = false
)

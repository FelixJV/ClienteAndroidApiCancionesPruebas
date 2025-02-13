package com.example.composefotosappfjv.ui.pantallaLogin

import com.example.composefotosappfjv.domain.modelo.User
import com.example.composefotosappfjv.ui.common.UiEvent

data class LoginState(
    val user: User? = null,
    val mensaje: String? = null,
    val event: UiEvent? = null,
    val isLoading: Boolean = false,
    val userEncontrado: Boolean = false
)

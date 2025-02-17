package com.example.composefotosappfjv.domain.modelo

data class User(
    var nombre: String = "",
    var password: String ="",
    var verificado: Boolean = false,
    var email:String = "",
)

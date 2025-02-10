package com.example.moviles1felixjv.data.remote.modelo

import com.example.moviles1felixjv.domain.domain.modelo.User

data class UsersItem(
    val id: Int,
    val name: String,
    val username: String,
)
fun UsersItem.toUser() =
    User(
        idUser = id,
        nombre =  name,
        usernameM = username
    )
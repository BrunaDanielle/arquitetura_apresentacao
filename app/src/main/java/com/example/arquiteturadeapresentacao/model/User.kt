package com.example.arquiteturadeapresentacao.model

import androidx.annotation.DrawableRes

data class User(
    @DrawableRes val profileImg: Int,
    val userName: String,
    val phoneNumber: String
)
package com.example.arquiteturadeapresentacao.userinfo.domain

import androidx.annotation.DrawableRes

data class User(
    val name: String,
    val phoneNumber: String,
    @DrawableRes val profileImg: Int,
)
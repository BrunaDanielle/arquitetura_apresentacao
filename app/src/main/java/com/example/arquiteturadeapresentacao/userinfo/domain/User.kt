package com.example.arquiteturadeapresentacao.userinfo.domain

import androidx.annotation.DrawableRes

data class User(
    val userName: String,
    val phoneNumber: String,
    @DrawableRes val profileImg: Int,
)
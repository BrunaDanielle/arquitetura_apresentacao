package com.example.arquiteturadeapresentacao.userinfo

import com.example.arquiteturadeapresentacao.userinfo.domain.User

data class UserInfoState(
    val isLoading: Boolean = false,
    val showingUserInfo: User? = null
)
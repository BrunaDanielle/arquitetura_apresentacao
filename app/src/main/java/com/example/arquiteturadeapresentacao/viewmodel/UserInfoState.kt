package com.example.arquiteturadeapresentacao.viewmodel

import com.example.arquiteturadeapresentacao.model.User

data class UserInfoState(
    val isLoading: Boolean = false,
    val showingUserInfo: User? = null
)
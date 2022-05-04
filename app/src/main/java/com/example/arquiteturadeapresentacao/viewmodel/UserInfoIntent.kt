package com.example.arquiteturadeapresentacao.viewmodel

sealed interface UserInfoIntent {
    object Loading : UserInfoIntent
    object SetUserInfo : UserInfoIntent
}
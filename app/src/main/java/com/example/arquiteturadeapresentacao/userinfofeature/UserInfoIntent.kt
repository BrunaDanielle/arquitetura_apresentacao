package com.example.arquiteturadeapresentacao.userinfofeature

sealed interface UserInfoIntent {
    object RetryGetUserInfo : UserInfoIntent
    object SeeUserData : UserInfoIntent
}
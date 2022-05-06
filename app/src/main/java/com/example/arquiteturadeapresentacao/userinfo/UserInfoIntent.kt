package com.example.arquiteturadeapresentacao.userinfo

sealed interface UserInfoIntent {
    object SeeUserData : UserInfoIntent
    class CallToUser(val phoneNumber: String) : UserInfoIntent
}
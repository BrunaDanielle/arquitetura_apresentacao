package com.example.arquiteturadeapresentacao.userinfo

sealed interface UserInfoIntent {
    object SeeUserData : UserInfoIntent
    object UpdateUserData : UserInfoIntent
    class CallToUser(val phoneNumber: String) : UserInfoIntent
}

sealed interface UserInfoAction {
    object GetUpdatedData : UserInfoAction
    class NavigateToCall(val phoneNumber: String) : UserInfoAction
}
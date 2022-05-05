package com.example.arquiteturadeapresentacao.userinfofeature

import com.example.arquiteturadeapresentacao.domain.User

sealed interface UserInfoState {
    object Loading : UserInfoState
    object Failure : UserInfoState
    class ShowingUserInfo(val user: User): UserInfoState
}

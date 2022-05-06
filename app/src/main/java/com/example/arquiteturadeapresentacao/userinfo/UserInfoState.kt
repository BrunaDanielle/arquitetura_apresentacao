package com.example.arquiteturadeapresentacao.userinfo

import com.example.arquiteturadeapresentacao.userinfo.domain.User

sealed interface UserInfoState {
    object Loading : UserInfoState
    object Failure : UserInfoState
    class ShowingUserInfo(val user: User): UserInfoState
}

package com.example.arquiteturadeapresentacao.viewmodel

import com.example.arquiteturadeapresentacao.model.User

sealed interface UserInfoState {
    object Loading : UserInfoState
    class ShowingUserInfo(val user: User): UserInfoState
}

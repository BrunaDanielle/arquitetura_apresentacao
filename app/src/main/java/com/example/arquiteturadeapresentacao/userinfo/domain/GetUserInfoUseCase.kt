package com.example.arquiteturadeapresentacao.userinfo.domain

import com.example.arquiteturadeapresentacao.R
import kotlinx.coroutines.delay

class GetUserInfoUseCase {

    suspend operator fun invoke(): User {
        delay(3000)
        return User(
            name = "Adam",
            phoneNumber = "(11)1111 - 1111",
            R.drawable.aluna
        )
    }
}
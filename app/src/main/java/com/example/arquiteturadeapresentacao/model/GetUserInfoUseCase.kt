package com.example.arquiteturadeapresentacao.model

import com.example.arquiteturadeapresentacao.R
import kotlinx.coroutines.delay

class GetUserInfoUseCase {

    suspend operator fun invoke(): User {
        delay(3000)
        return User(
            R.drawable.aluna,
            userName = "Adam",
            phoneNumber = "(11)1111 - 1111"
        )
    }
}
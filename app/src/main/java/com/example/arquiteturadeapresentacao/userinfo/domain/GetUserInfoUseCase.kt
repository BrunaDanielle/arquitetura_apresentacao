package com.example.arquiteturadeapresentacao.userinfo.domain

import com.example.arquiteturadeapresentacao.R
import kotlinx.coroutines.delay

class GetUserInfoUseCase {

    suspend operator fun invoke(): User {
        delay(3000)
        if ((0..10).random() <= 4) {
            throw Exception("Unexpected error")
        } else {
            return User(
                name ="Adam",
                phoneNumber = "(11)1111 - 1111",
                profileImg = R.drawable.aluna
            )
        }
    }
}
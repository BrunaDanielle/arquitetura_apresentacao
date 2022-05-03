package com.example.arquiteturadeapresentacao.model

import com.example.arquiteturadeapresentacao.R
import com.example.arquiteturadeapresentacao.contract.Contract
import kotlinx.coroutines.delay

class GetUserInfoUseCase: Contract.Model {

    override suspend operator fun invoke(): User {
        delay(3000)
        return User(
            R.drawable.aluna,
            userName = "Adam",
            phoneNumber = "(11)1111 - 1111"
        )
    }
}
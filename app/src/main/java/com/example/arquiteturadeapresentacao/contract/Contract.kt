package com.example.arquiteturadeapresentacao.contract

import androidx.annotation.DrawableRes
import com.example.arquiteturadeapresentacao.model.User

interface Contract {
    interface View {
        fun showLoading(isLoading: Boolean)
        fun bindViews()
        fun setUser(
            @DrawableRes profileImg: Int,
            userName: String,
            phoneNumber: String
        )
        fun setListener()
    }

    interface Presenter {
        fun start()
    }

    interface Model {
        suspend operator fun invoke(): User
    }
}
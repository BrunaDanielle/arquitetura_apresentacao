package com.example.arquiteturadeapresentacao.contract

import androidx.annotation.DrawableRes
import com.example.arquiteturadeapresentacao.model.User

interface Contract {
    interface View {
        fun showLoading(isLoading: Boolean)
        fun setComponentsVisibility(isVisible: Boolean)
        fun showButtonRetry(isVisible: Boolean)
        fun showUserData(
            @DrawableRes profileImg: Int,
            userName: String,
            phoneNumber: String
        )
    }

    interface Presenter {
        fun onViewCreated()
        fun onRetryClicked()
    }
}
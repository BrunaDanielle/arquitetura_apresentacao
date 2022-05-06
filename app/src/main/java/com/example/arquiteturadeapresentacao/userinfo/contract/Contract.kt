package com.example.arquiteturadeapresentacao.userinfo.contract

import androidx.annotation.DrawableRes

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
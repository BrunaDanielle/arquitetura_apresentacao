package com.example.arquiteturadeapresentacao.userinfo

import androidx.annotation.DrawableRes

interface Contract {
    interface View {
        fun setLoadingVisibility(isVisible: Boolean)
        fun setSuccessVisibility(isVisible: Boolean)
        fun setEmptyStateVisibility(isVisible: Boolean)
        fun setUserData(
            @DrawableRes profileImg: Int,
            userName: String,
            phoneNumber: String
        )
        fun navigateToCall()
    }

    interface Presenter {
        fun onViewCreated()
        fun onCallClicked()
        fun onRetryClicked()
    }
}
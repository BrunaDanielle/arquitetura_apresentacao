package com.example.arquiteturadeapresentacao.userinfo

import com.example.arquiteturadeapresentacao.userinfo.domain.GetUserInfoUseCase
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class UserInfoPresenter(private val view: Contract.View) : Contract.Presenter {

    private val getUserInfoUseCase = GetUserInfoUseCase()
    private val scope = MainScope()

    override fun onViewCreated() {
        requestUserData()
    }

    override fun onCallClicked() {
        view.navigateToCall()
    }

    override fun onRetryClicked() {
        requestUserData()
    }

    private fun requestUserData() {
        showLoading()
        scope.launch {
            try {
                val user = getUserInfoUseCase()
                view.setUserData(
                    profileImg = user.profileImg,
                    userName = user.userName,
                    phoneNumber = user.phoneNumber
                )
                showSuccess()
            } catch (e: Exception) {
                showEmptyState()
            }
        }
    }

    private fun showLoading() {
        view.setLoadingVisibility(true)
        view.setEmptyStateVisibility(false)
        view.setSuccessVisibility(false)
    }

    private fun showSuccess() {
        view.setLoadingVisibility(false)
        view.setEmptyStateVisibility(false)
        view.setSuccessVisibility(true)
    }

    private fun showEmptyState() {
        view.setLoadingVisibility(false)
        view.setEmptyStateVisibility(true)
        view.setSuccessVisibility(false)
    }
}
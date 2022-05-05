package com.example.arquiteturadeapresentacao.presenter

import com.example.arquiteturadeapresentacao.contract.Contract
import com.example.arquiteturadeapresentacao.model.GetUserInfoUseCase
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class UserInfoPresenter(private val view: Contract.View) : Contract.Presenter {

    private val useCase = GetUserInfoUseCase()
    private val scope = MainScope()

    override fun onViewCreated() {
        showingLoad()
        requestUserData()
    }

    override fun onRetryClicked() {
        showingLoad()
        requestUserData()
    }

    private fun requestUserData() {
        scope.launch {
            val user = useCase()

            val randomNumber = (0..10).random()
            if (randomNumber < 4) {
                showingError()
            } else {
                hidingLoad()
                view.showUserData(
                    profileImg = user.profileImg,
                    userName = user.userName,
                    phoneNumber = user.phoneNumber
                )
            }
        }
    }

    private fun showingLoad() {
        view.showLoading(isLoading = true)
        view.showButtonRetry(false)
        view.setComponentsVisibility(false)
    }

    private fun hidingLoad() {
        view.showLoading(isLoading = false)
        view.showButtonRetry(false)
        view.setComponentsVisibility(true)
    }

    private fun showingError() {
        view.showLoading(isLoading = false)
        view.showButtonRetry(true)
        view.setComponentsVisibility(false)
    }
}
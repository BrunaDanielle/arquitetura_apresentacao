package com.example.arquiteturadeapresentacao.presenter

import com.example.arquiteturadeapresentacao.contract.Contract
import com.example.arquiteturadeapresentacao.model.GetUserInfoUseCase
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class UserInfoPresenter(private val view: Contract.View) : Contract.Presenter {

    private val useCase = GetUserInfoUseCase()
    private val scope = MainScope()

    override fun onViewCreated() {
        view.showLoading(isLoading = true)
        scope.launch {
            val user = useCase()

            view.showUserData(
                profileImg = user.profileImg,
                userName = user.userName,
                phoneNumber = user.phoneNumber
            )

            view.showLoading(isLoading = false)
        }
    }
}
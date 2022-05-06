package com.example.arquiteturadeapresentacao.userinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arquiteturadeapresentacao.userinfo.domain.GetUserInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserInfoViewModel(
    private val getUserInfoUseCase: GetUserInfoUseCase
) : ViewModel() {

    private val userInfoMutableLiveData: MutableLiveData<UserInfoState> = MutableLiveData()
    val userInfoLiveData: LiveData<UserInfoState> = userInfoMutableLiveData

    private val navigateToCallMutableLiveData: MutableLiveData<SetAction<String>> = MutableLiveData()
    val navigateToCallLiveData: LiveData<SetAction<String>> = navigateToCallMutableLiveData

    fun sendIntent(intent: UserInfoIntent) {
        val action = when (intent) {
            is UserInfoIntent.SeeUserData -> UserInfoAction.GetUpdatedData
            is UserInfoIntent.UpdateUserData -> UserInfoAction.GetUpdatedData
            is UserInfoIntent.CallToUser -> UserInfoAction.NavigateToCall(intent.phoneNumber)
        }
        handleAction(action)
    }

    private fun handleAction(action: UserInfoAction) {
        when(action) {
            is UserInfoAction.GetUpdatedData -> loadUserInfo()
            is UserInfoAction.NavigateToCall -> navigateToCall(action.phoneNumber)
        }
    }

    private fun loadUserInfo() {
        userInfoMutableLiveData.value = UserInfoState.Loading
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val user = getUserInfoUseCase()
                    userInfoMutableLiveData.postValue(
                        UserInfoState.ShowingUserInfo(user)
                    )
                } catch (e: Exception) {
                    userInfoMutableLiveData.postValue(UserInfoState.Failure)
                }
            }
        }
    }

    private fun navigateToCall(phoneNumber: String) {
        navigateToCallMutableLiveData.value = SetAction(phoneNumber)
    }
}
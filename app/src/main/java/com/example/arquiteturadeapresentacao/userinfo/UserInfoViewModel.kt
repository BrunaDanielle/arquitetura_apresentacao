package com.example.arquiteturadeapresentacao.userinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arquiteturadeapresentacao.userinfo.domain.GetUserInfoUseCase
import com.example.arquiteturadeapresentacao.userinfo.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserInfoViewModel(
    private val getUserInfoUseCase: GetUserInfoUseCase
) : ViewModel() {

    private val userInfoMutableLiveData: MutableLiveData<UserInfoState> = MutableLiveData()
    val userInfoLiveData: LiveData<UserInfoState> = userInfoMutableLiveData

    private val navigateToCallMutableLiveData: MutableLiveData<String> = MutableLiveData()
    val navigateToCallLiveData: LiveData<String> = navigateToCallMutableLiveData

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        userInfoMutableLiveData.value = UserInfoState(isLoading = true)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val user = getUserInfoUseCase.invoke()
                    userInfoMutableLiveData.postValue(
                        UserInfoState(
                            isLoading = false,
                            User(
                                profileImg = user.profileImg,
                                name = user.name,
                                phoneNumber = user.phoneNumber
                            )
                        )
                    )
                } catch (e: Exception) {
                    userInfoMutableLiveData.postValue(UserInfoState(hasError = true))
                }
            }
        }
    }

    fun onCallClicked(phoneNumber: String) {
        // TODO usar classe de Action
        navigateToCallMutableLiveData.value = phoneNumber
    }

    fun onRetryClicked() {
        getUserInfo()
    }
}
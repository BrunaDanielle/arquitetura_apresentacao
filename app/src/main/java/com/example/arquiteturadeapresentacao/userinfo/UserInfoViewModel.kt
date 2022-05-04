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
    private val useCase: GetUserInfoUseCase
) : ViewModel() {

    private val userInfoMutableLiveData: MutableLiveData<UserInfoState> = MutableLiveData()
    val userInfoLiveData: LiveData<UserInfoState> = userInfoMutableLiveData

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        userInfoMutableLiveData.value = UserInfoState(isLoading = true)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val user = useCase.invoke()
                userInfoMutableLiveData.postValue(
                    UserInfoState(
                        isLoading = false,
                        User(
                            profileImg = user.profileImg,
                            userName = user.userName,
                            phoneNumber = user.phoneNumber
                        )
                    )
                )
            }
        }
    }
}
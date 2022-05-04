package com.example.arquiteturadeapresentacao.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arquiteturadeapresentacao.model.GetUserInfoUseCase
import com.example.arquiteturadeapresentacao.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserInfoViewModel(
    private val useCase: GetUserInfoUseCase
) : ViewModel() {

    private val _userInfo: MutableLiveData<UserInfoState> = MutableLiveData()
    val userInfo: LiveData<UserInfoState> = _userInfo

    fun loadUserInfo() {
        _userInfo.value = UserInfoState(isLoading = true)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val user = useCase.invoke()
                _userInfo.postValue(
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
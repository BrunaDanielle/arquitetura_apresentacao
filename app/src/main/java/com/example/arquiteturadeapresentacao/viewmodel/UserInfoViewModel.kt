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

    private fun loadUserInfo() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val user = useCase.invoke()
                _userInfo.postValue(
                    UserInfoState.ShowingUserInfo(
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

    fun sendIntent(intent: UserInfoIntent) {
        when(intent) {
            UserInfoIntent.Loading -> _userInfo.value = UserInfoState.Loading
            is UserInfoIntent.SetUserInfo -> loadUserInfo()
        }
    }
}
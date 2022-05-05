package com.example.arquiteturadeapresentacao.userinfofeature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arquiteturadeapresentacao.domain.GetUserInfoUseCase
import com.example.arquiteturadeapresentacao.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserInfoViewModel(
    private val useCase: GetUserInfoUseCase
) : ViewModel() {

    private val userInfoMutableLiveData: MutableLiveData<UserInfoState> = MutableLiveData()
    val userInfoLiveData: LiveData<UserInfoState> = userInfoMutableLiveData

    private fun loadUserInfo() {
        userInfoMutableLiveData.value = UserInfoState.Loading
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val user = useCase.invoke()

                val randomNumber = (0 until 10).random()
                if (randomNumber < 4) {
                    userInfoMutableLiveData.postValue(UserInfoState.Failure)
                } else {
                    userInfoMutableLiveData.postValue(
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
    }

    fun sendIntent(intent: UserInfoIntent) {
        when (intent) {
            UserInfoIntent.RetryGetUserInfo -> loadUserInfo()
            is UserInfoIntent.SeeUserData -> loadUserInfo()
        }
    }
}
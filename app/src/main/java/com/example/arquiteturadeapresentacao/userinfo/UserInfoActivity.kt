package com.example.arquiteturadeapresentacao.userinfo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.arquiteturadeapresentacao.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModel<UserInfoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.sendIntent(UserInfoIntent.SeeUserData)

        setStateObserver()
        setListeners()
        setActionObserver()
    }

    private fun setListeners() {
        binding.btnCall.setOnClickListener {
            viewModel.sendIntent(UserInfoIntent.CallToUser(binding.tvPhoneNumber.text.toString()))
        }

        binding.btnRetry.setOnClickListener {
            viewModel.sendIntent(UserInfoIntent.SeeUserData)
        }
    }

    private fun setStateObserver() {
        viewModel.userInfoLiveData.observe(this) { state ->
            when (state) {
                is UserInfoState.Loading -> {
                    setSuccessVisibility(false)
                    setEmptyStateVisibility(false)
                    setLoadingVisibility(true)
                }
                is UserInfoState.ShowingUserInfo -> {
                    setLoadingVisibility(false)
                    setEmptyStateVisibility(false)
                    setSuccessVisibility(true)
                    setUserData(
                        profileImg = state.user.profileImg,
                        userName = state.user.name,
                        phoneNumber = state.user.phoneNumber
                    )
                }
                is UserInfoState.Failure -> {
                    setLoadingVisibility(false)
                    setSuccessVisibility(false)
                    setEmptyStateVisibility(true)
                }
            }
        }
    }

    private fun setActionObserver() {
        viewModel.navigateToCallLiveData.observe(this) { phoneNumber ->
            phoneNumber.sendActionIfNotHandled()?.let {
                navigateToCall(it)
            }
        }
    }

    private fun setSuccessVisibility(isVisible: Boolean) {
        with(binding) {
            ivUserPhoto.isVisible = isVisible
            tvName.isVisible = isVisible
            tvPhoneNumber.isVisible = isVisible
            btnCall.isVisible = isVisible
        }
    }

    private fun setLoadingVisibility(isLoading: Boolean) {
        binding.pbLoading.isVisible = isLoading
    }

    private fun setEmptyStateVisibility(isVisible: Boolean) {
        binding.btnRetry.isVisible = isVisible
    }

    private fun setUserData(profileImg: Int, userName: String, phoneNumber: String) {
        with(binding) {
            ivUserPhoto.setImageResource(profileImg)
            tvName.text = userName
            tvPhoneNumber.text = phoneNumber
        }
    }

    private fun navigateToCall(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")
        startActivity(intent)
    }
}
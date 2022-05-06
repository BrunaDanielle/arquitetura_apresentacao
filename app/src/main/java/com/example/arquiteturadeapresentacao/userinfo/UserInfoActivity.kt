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

        setListeners()
        setStateObserver()
        setActionObserver()
    }

    private fun setListeners() {
        binding.btnCall.setOnClickListener {
            viewModel.onCallClicked(binding.tvPhoneNumber.text.toString())
        }

        binding.btnRetry.setOnClickListener {
            viewModel.onRetryClicked()
        }
    }

    private fun setStateObserver() {
        viewModel.userInfoLiveData.observe(this) { state ->
            when {
                state.isLoading -> {
                    setSuccessVisibility(false)
                    setEmptyStateVisibility(false)
                    setLoadingVisibility(true)
                }
                state.hasError -> {
                    setLoadingVisibility(false)
                    setSuccessVisibility(false)
                    setEmptyStateVisibility(true)
                }
                else -> {
                    setEmptyStateVisibility(false)
                    setLoadingVisibility(false)
                    setSuccessVisibility(true)
                    state.showingUserInfo?.let { user ->
                        setUserData(
                            profileImg = user.profileImg,
                            userName = user.name,
                            phoneNumber = user.phoneNumber
                        )
                    }
                }
            }
        }
    }

    private fun setActionObserver() {
        viewModel.navigateToCallLiveData.observe(this) { phoneNumber ->
            navigateToCall(phoneNumber)
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

    private fun setLoadingVisibility(isVisible: Boolean) {
        binding.pbLoading.isVisible = isVisible
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
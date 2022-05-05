package com.example.arquiteturadeapresentacao.userinfo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.arquiteturadeapresentacao.R
import com.example.arquiteturadeapresentacao.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserInfoActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<UserInfoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
        setStateObserver()
    }

    private fun setListeners() {
        binding.btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + binding.tvPhoneNumber.text.toString())
            startActivity(intent)
        }

        binding.btnRetry.setOnClickListener{
            viewModel.onRetryClicked()
        }
    }

    private fun setStateObserver() {
        viewModel.userInfoLiveData.observe(this) { state ->

            when {
                state.isLoading.and(state.hasError.not()) -> {
                    showLoading(true)
                    setComponentsVisibility(false)
                    showButtonTryAgain(false)
                }
                state.hasError.and(state.isLoading.not()) -> {
                    showButtonTryAgain(true)
                    showLoading(false)
                    setComponentsVisibility(false)
                }
                else -> {
                    showButtonTryAgain(false)
                    showLoading(false)
                    setComponentsVisibility(true)
                }
            }

            state.showingUserInfo?.let { user ->
                showUserData(
                    profileImg = user.profileImg,
                    userName = user.name,
                    phoneNumber = user.phoneNumber
                )
            }
        }
    }

    private fun setComponentsVisibility(isVisible: Boolean) {
        with(binding) {
            ivUserPhoto.isVisible = isVisible
            tvName.isVisible = isVisible
            tvPhoneNumber.isVisible = isVisible
            btnCall.isVisible = isVisible
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pbLoading.isVisible = isLoading
    }

    private fun showButtonTryAgain(isVisible: Boolean) {
        binding.btnRetry.isVisible = isVisible
    }

    private fun showUserData(profileImg: Int, userName: String, phoneNumber: String) {
        with(binding) {
            ivUserPhoto.setImageResource(profileImg)
            tvName.text = userName
            tvPhoneNumber.text = phoneNumber
        }
    }
}
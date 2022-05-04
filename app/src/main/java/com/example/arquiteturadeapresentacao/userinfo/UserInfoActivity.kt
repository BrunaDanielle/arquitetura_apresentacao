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
    }

    private fun setListeners() {
        binding.btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + binding.tvPhoneNumber.text.toString())
            startActivity(intent)
        }
    }

    private fun setStateObserver() {
        viewModel.userInfoLiveData.observe(this) { state ->
            showLoading(state.isLoading)

            state.showingUserInfo?.let { user ->
                showUserData(
                    profileImg = user.profileImg,
                    userName = user.name,
                    phoneNumber = user.phoneNumber
                )
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        with(binding) {
            pbLoading.isVisible = isLoading
            ivUserPhoto.isVisible = isLoading.not()
            tvName.isVisible = isLoading.not()
            tvPhoneNumber.isVisible = isLoading.not()
            btnCall.isVisible = isLoading.not()
        }
    }

    private fun showUserData(profileImg: Int, userName: String, phoneNumber: String) {
        with(binding){
            ivUserPhoto.setImageResource(profileImg)
            tvName.text = userName
            tvPhoneNumber.text = phoneNumber
        }
    }
}
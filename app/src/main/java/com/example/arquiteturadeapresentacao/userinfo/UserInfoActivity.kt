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
    private val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
    private val viewModel by viewModel<UserInfoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(binding.root)

        setStateObserver()
        setListener()
    }

    private fun setStateObserver() {
        viewModel.userInfoLiveData.observe(this) { state ->
            showLoading(state.isLoading)

            state.showingUserInfo?.let { user ->
                setUser(
                    profileImg = user.profileImg,
                    userName = user.userName,
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

    private fun setUser(profileImg: Int, userName: String, phoneNumber: String) {
        with(binding){
            ivUserPhoto.setImageResource(profileImg)
            tvName.text = userName
            tvPhoneNumber.text = phoneNumber
        }
    }

    private fun setListener() {
        binding.btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + binding.tvPhoneNumber.text.toString())
            startActivity(intent)
        }
    }
}
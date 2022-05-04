package com.example.arquiteturadeapresentacao.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.arquiteturadeapresentacao.R
import com.example.arquiteturadeapresentacao.databinding.ActivityMainBinding
import com.example.arquiteturadeapresentacao.viewmodel.UserInfoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModel<UserInfoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.loadUserInfo()

        setStateObserver()
        setListener()
    }

    private fun setStateObserver() {
        viewModel.userInfo.observe(this) { state ->
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
            progressBar.isVisible = isLoading
            userPhoto.isVisible = isLoading.not()
            name.isVisible = isLoading.not()
            phoneNumber.isVisible = isLoading.not()
            call.isVisible = isLoading.not()
        }
    }

    private fun setUser(profileImg: Int, userName: String, phoneNumber: String) {
        binding.userPhoto.setImageResource(profileImg)
        binding.name.text = userName
        binding.phoneNumber.text = phoneNumber
    }

    private fun setListener() {
        binding.call.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + binding.phoneNumber.text.toString())
            resultLauncher.launch(intent)
        }
    }

    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}
}
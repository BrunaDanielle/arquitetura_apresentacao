package com.example.arquiteturadeapresentacao.userinfo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.arquiteturadeapresentacao.R
import com.example.arquiteturadeapresentacao.userinfo.contract.Contract
import com.example.arquiteturadeapresentacao.databinding.ActivityMainBinding


class UserInfoActivity : AppCompatActivity(R.layout.activity_main), Contract.View {
    private lateinit var binding: ActivityMainBinding
    private var presenter: UserInfoPresenter = UserInfoPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
        presenter.onViewCreated()
    }

    override fun showLoading(isLoading: Boolean) {
        binding.pbLoading.isVisible = isLoading
    }

    override fun setComponentsVisibility(isVisible: Boolean) {
        with(binding) {
            ivUserPhoto.isVisible = isVisible
            tvName.isVisible = isVisible
            tvPhoneNumber.isVisible = isVisible
            btnCall.isVisible = isVisible
        }
    }

    override fun showButtonRetry(isVisible: Boolean) {
        binding.btnRetry.isVisible = isVisible
    }

    override fun showUserData(profileImg: Int, userName: String, phoneNumber: String) {
        with(binding) {
            ivUserPhoto.setImageResource(profileImg)
            tvName.text = userName
            tvPhoneNumber.text = phoneNumber
        }
    }

    private fun setListeners() {
        binding.btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + binding.tvPhoneNumber.text.toString())
            startActivity(intent)
        }

        binding.btnRetry.setOnClickListener{
            presenter.onRetryClicked()
        }
    }
}
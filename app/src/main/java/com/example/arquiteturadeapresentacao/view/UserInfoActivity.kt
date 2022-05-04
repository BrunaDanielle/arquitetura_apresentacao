package com.example.arquiteturadeapresentacao.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.arquiteturadeapresentacao.R
import com.example.arquiteturadeapresentacao.contract.Contract
import com.example.arquiteturadeapresentacao.databinding.ActivityMainBinding
import com.example.arquiteturadeapresentacao.presenter.UserInfoPresenter


class UserInfoActivity : AppCompatActivity(R.layout.activity_main), Contract.View {
    private lateinit var binding: ActivityMainBinding
    private var presenter: UserInfoPresenter = UserInfoPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListener()
        presenter.onViewCreated()
    }

    override fun showLoading(isLoading: Boolean) {
        with(binding) {
            pbLoading.isVisible = isLoading
            ivUserPhoto.isVisible = isLoading.not()
            tvName.isVisible = isLoading.not()
            tvPhoneNumber.isVisible = isLoading.not()
            btnCall.isVisible = isLoading.not()
        }
    }

    override fun showUserData(profileImg: Int, userName: String, phoneNumber: String) {
        binding.ivUserPhoto.setImageResource(profileImg)
        binding.tvName.text = userName
        binding.tvPhoneNumber.text = phoneNumber
    }

    private fun setListener() {
        binding.btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + binding.tvPhoneNumber.text.toString())
            startActivity(intent)
        }
    }
}
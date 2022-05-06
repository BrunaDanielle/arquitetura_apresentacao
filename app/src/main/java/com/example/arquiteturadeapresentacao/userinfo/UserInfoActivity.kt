package com.example.arquiteturadeapresentacao.userinfo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.arquiteturadeapresentacao.databinding.ActivityMainBinding

class UserInfoActivity : AppCompatActivity(), Contract.View {
    private lateinit var binding: ActivityMainBinding
    private var presenter: Contract.Presenter = UserInfoPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
        presenter.onViewCreated()
    }

    private fun setListeners() {
        binding.btnCall.setOnClickListener {
            presenter.onCallClicked(binding.tvPhoneNumber.text.toString())
        }

        binding.btnRetry.setOnClickListener{
            presenter.onRetryClicked()
        }
    }

    override fun setLoadingVisibility(isVisible: Boolean) {
        binding.pbLoading.isVisible = isVisible
    }

    override fun setSuccessVisibility(isVisible: Boolean) {
        with(binding) {
            ivUserPhoto.isVisible = isVisible
            tvName.isVisible = isVisible
            tvPhoneNumber.isVisible = isVisible
            btnCall.isVisible = isVisible
        }
    }

    override fun setEmptyStateVisibility(isVisible: Boolean) {
        binding.btnRetry.isVisible = isVisible
    }

    override fun setUserData(profileImg: Int, userName: String, phoneNumber: String) {
        with(binding) {
            ivUserPhoto.setImageResource(profileImg)
            tvName.text = userName
            tvPhoneNumber.text = phoneNumber
        }
    }

    override fun navigateToCall(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")
        startActivity(intent)
    }
}
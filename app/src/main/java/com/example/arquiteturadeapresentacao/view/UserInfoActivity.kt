package com.example.arquiteturadeapresentacao.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.arquiteturadeapresentacao.R
import com.example.arquiteturadeapresentacao.contract.Contract
import com.example.arquiteturadeapresentacao.databinding.ActivityMainBinding
import com.example.arquiteturadeapresentacao.presenter.UserInfoPresenter


class UserInfoActivity : AppCompatActivity(), Contract.View {
    private lateinit var binding: ActivityMainBinding
    private var presenter: UserInfoPresenter = UserInfoPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.start()
    }

    override fun showLoading(isLoading: Boolean) {
        with(binding) {
            progressBar.isVisible = isLoading
            userPhoto.isVisible = isLoading.not()
            name.isVisible = isLoading.not()
            phoneNumber.isVisible = isLoading.not()
            call.isVisible = isLoading.not()
        }
    }

    override fun setUser(profileImg: Int, userName: String, phoneNumber: String) {
        binding.userPhoto.setImageResource(profileImg)
        binding.name.text = userName
        binding.phoneNumber.text = phoneNumber
    }

    override fun bindViews() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun setListener() {
        binding.call.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + binding.phoneNumber.text.toString())
            resultLauncher.launch(intent)
        }
    }

    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}
}
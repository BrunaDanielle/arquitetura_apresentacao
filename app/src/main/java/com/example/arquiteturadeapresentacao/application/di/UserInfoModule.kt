package com.example.arquiteturadeapresentacao.application.di

import com.example.arquiteturadeapresentacao.userinfo.domain.GetUserInfoUseCase
import com.example.arquiteturadeapresentacao.userinfo.UserInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object UserInfoModule {
    val presentationModule = module {
        viewModel {
            UserInfoViewModel(GetUserInfoUseCase())
        }
    }
}
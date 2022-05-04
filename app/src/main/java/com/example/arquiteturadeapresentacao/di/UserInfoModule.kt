package com.example.arquiteturadeapresentacao.di

import com.example.arquiteturadeapresentacao.model.GetUserInfoUseCase
import com.example.arquiteturadeapresentacao.viewmodel.UserInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object UserInfoModule {
    val presentationModule = module {
        viewModel {
            UserInfoViewModel(GetUserInfoUseCase())
        }
    }
}
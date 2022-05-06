package com.example.arquiteturadeapresentacao.userinfo

open class SetAction<T>(private val action: T) {
    var hasBeenHandled = false
        private set

    fun sendActionIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            action
        }
    }
}
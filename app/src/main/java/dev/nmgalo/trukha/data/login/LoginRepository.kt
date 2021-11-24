package dev.nmgalo.trukha.data.login

import dev.nmgalo.trukha.ui.login.UserCredentials

interface LoginRepository {
    fun auth(userCredentials: UserCredentials)
}

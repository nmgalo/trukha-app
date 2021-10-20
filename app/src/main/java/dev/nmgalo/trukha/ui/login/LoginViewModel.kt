package dev.nmgalo.trukha.ui.login

import dev.nmgalo.trukha.data.ApiClient
import dev.nmgalo.trukha.ui.library.viewModel.ViewModel
import dev.nmgalo.trukha.ui.utils.CommonRequestResult

class LoginViewModel : ViewModel() {
    fun signIn(userCredentials: UserCredentials): CommonRequestResult =
        ApiClient.auth(userCredentials)
}

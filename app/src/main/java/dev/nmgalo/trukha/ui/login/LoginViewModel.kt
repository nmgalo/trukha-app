package dev.nmgalo.trukha.ui.login

import dev.nmgalo.trukha.data.login.LoginRepository
import dev.nmgalo.trukha.data.login.UserNotFoundException
import dev.nmgalo.trukha.pool.ThreadPool
import dev.nmgalo.trukha.ui.library.hotdata.HotData
import dev.nmgalo.trukha.ui.library.viewModel.ViewModel
import dev.nmgalo.trukha.ui.utils.CommonRequestResult

class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val threadPool: ThreadPool
) : ViewModel() {

    val isAuthenticated = HotData<Boolean>()
    val isLoading = HotData(false)

    fun signIn(userCredentials: UserCredentials) {
        threadPool.launch {
            isLoading.setValue(true)
            try {
                loginRepository.auth(userCredentials)
                isAuthenticated.setValue(true)
            } catch (e: UserNotFoundException) {
                isAuthenticated.setValue(false)
            }
            isLoading.setValue(false)
        }
    }
}

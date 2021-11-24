package dev.nmgalo.trukha.ui.library.viewModel.factory

import dev.nmgalo.trukha.data.login.LoginRepository
import dev.nmgalo.trukha.pool.ThreadPool
import dev.nmgalo.trukha.ui.library.viewModel.ViewModel
import dev.nmgalo.trukha.ui.library.viewModel.ViewModelStore
import dev.nmgalo.trukha.ui.login.LoginViewModel

class LoginViewModelFactory(
    private val loginRepository: LoginRepository,
    private val threadPool: ThreadPool
) : ViewModelStore.Factory {
    override fun <V : ViewModel> create(modelClass: Class<V>): V {
        return LoginViewModel(loginRepository, threadPool) as V
    }
}

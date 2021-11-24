package dev.nmgalo.trukha.di

import android.content.Context
import dev.nmgalo.trukha.data.ApiClient
import dev.nmgalo.trukha.data.CharactersRepository
import dev.nmgalo.trukha.data.CharactersRepositoryImpl
import dev.nmgalo.trukha.data.login.LoginRepository
import dev.nmgalo.trukha.data.login.LoginRepositoryImpl
import dev.nmgalo.trukha.pool.ThreadPool
import dev.nmgalo.trukha.ui.utils.json.JsonHelper

class AppContainer(context: Context) {
    private val networkClient = ApiClient
    private val jsonHelper = JsonHelper

    val charactersRepository: CharactersRepository = CharactersRepositoryImpl(
        service = networkClient,
        jsonHelper = jsonHelper
    )

    val loginRepository: LoginRepository = LoginRepositoryImpl(
        apiClient = networkClient,
        jsonHelper = jsonHelper,
        sharedPreferenceModule = SharedPreferenceModule(context)
    )

    val threadPool by lazy {
        val cores = Runtime.getRuntime().availableProcessors()
        ThreadPool(cores, cores * 2)
    }
}

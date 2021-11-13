package dev.nmgalo.trukha.di

import dev.nmgalo.trukha.data.ApiClient
import dev.nmgalo.trukha.data.CharactersRepository
import dev.nmgalo.trukha.data.CharactersRepositoryImpl
import dev.nmgalo.trukha.ui.utils.json.JsonHelper

class AppContainer {
    private val networkClient = ApiClient
    private val jsonHelper = JsonHelper

    val charactersRepository: CharactersRepository =
        CharactersRepositoryImpl(networkClient, jsonHelper)
}

package dev.nmgalo.trukha.ui.library.viewModel.factory

import dev.nmgalo.trukha.data.CharactersRepository
import dev.nmgalo.trukha.ui.library.viewModel.ViewModel
import dev.nmgalo.trukha.ui.library.viewModel.ViewModelStore

class CharactersViewModelProviderFactory(
    private val charactersRepository: CharactersRepository
) : ViewModelStore.Factory {
    override fun <V : ViewModel> create(modelClass: Class<V>): V {
        return modelClass.getConstructor(charactersRepository::class.java)
            .newInstance(charactersRepository)
    }
}
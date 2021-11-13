package dev.nmgalo.trukha.ui.characters

import dev.nmgalo.trukha.data.CharactersRepository
import dev.nmgalo.trukha.ui.characters.model.CharactersUIModel
import dev.nmgalo.trukha.ui.library.hotdata.HotData
import dev.nmgalo.trukha.ui.library.viewModel.ViewModel

class CharactersViewModel(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    private var pageNumber = 1

    init {
        fetchNext()
    }

    val characters = HotData<List<CharactersUIModel>>()

    fun fetchNext() {
        characters.setValue(charactersRepository.get(++pageNumber).map { it.toUIModel() })
    }

}

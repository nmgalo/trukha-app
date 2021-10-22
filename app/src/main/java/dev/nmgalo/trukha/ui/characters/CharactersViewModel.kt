package dev.nmgalo.trukha.ui.characters

import dev.nmgalo.trukha.ui.characters.model.CharactersUIModel
import dev.nmgalo.trukha.ui.library.viewModel.ViewModel
import dev.nmgalo.trukha.ui.utils.observer.Subject

class CharactersViewModel : ViewModel() {

    val charactersObserver = Subject<List<CharactersUIModel>>()

    init {
        charactersObserver.notify(listOf(
            CharactersUIModel(1, "Nick"),
            CharactersUIModel(1, "jemala")
        ))
    }

}

package dev.nmgalo.trukha.ui.characters

import dev.nmgalo.trukha.ui.characters.model.CharactersUIModel
import dev.nmgalo.trukha.ui.library.viewModel.ViewModel
import dev.nmgalo.trukha.ui.utils.delay
import dev.nmgalo.trukha.ui.utils.observer.Subject

class CharactersViewModel : ViewModel() {

    val characters = Subject<List<CharactersUIModel>>()

    fun getCharacters() {
        characters.notify(
            listOf(
                CharactersUIModel(1, "Nick"),
                CharactersUIModel(2, "zaura"),
                CharactersUIModel(3, "Magula"),
                CharactersUIModel(4, "Tarzana"),
                CharactersUIModel(5, "Jane"),
            )
        )

        delay(2000) {
            characters.notify(
                listOf(
                    CharactersUIModel(6, "peter"),
                    CharactersUIModel(7, "parker"),
                )
            )
        }
    }

}

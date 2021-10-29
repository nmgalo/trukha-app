package dev.nmgalo.trukha.ui.characters

import dev.nmgalo.trukha.ui.characters.model.CharactersUIModel
import dev.nmgalo.trukha.ui.library.hotdata.HotData
import dev.nmgalo.trukha.ui.library.viewModel.ViewModel
import dev.nmgalo.trukha.ui.utils.delay

class CharactersViewModel : ViewModel() {

    val characters = HotData<List<CharactersUIModel>>()

    init {
        characters.setValue(
            listOf(
                CharactersUIModel(1, "Nick"),
                CharactersUIModel(2, "zaura"),
                CharactersUIModel(3, "Magula"),
                CharactersUIModel(4, "Tarzana"),
                CharactersUIModel(5, "Jane"),
            )
        )

        delay(2000) {
            characters.setValue(
                listOf(
                    CharactersUIModel(6, "peter"),
                    CharactersUIModel(7, "parker"),
                )
            )
        }

    }

}

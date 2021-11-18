package dev.nmgalo.trukha.ui.characters

import dev.nmgalo.trukha.data.CharactersRepository
import dev.nmgalo.trukha.pool.ThreadPool
import dev.nmgalo.trukha.ui.characters.model.CharactersUIModel
import dev.nmgalo.trukha.ui.library.hotdata.HotData
import dev.nmgalo.trukha.ui.library.viewModel.ViewModel

class CharactersViewModel(
    private val charactersRepository: CharactersRepository,
    private val threadPool: ThreadPool
) : ViewModel() {

    private var pageNumber = 0

    val isLoading = HotData<Boolean>()

    init {
        isLoading.setValue(false)
        fetchNext()
    }

    val characters = HotData<List<CharactersUIModel>>()


    fun fetchNext() {
        threadPool.launch {
            isLoading.setValue(true)
            characters.setValue(charactersRepository.get(++pageNumber * 10).map { it.toUIModel() })
            isLoading.setValue(false)
        }
    }

}

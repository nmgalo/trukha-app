package dev.nmgalo.trukha.ui.library.viewModel

import java.util.HashMap

class ViewModelStore {

    private val viewModelsMap = HashMap<String, ViewModel>()

    fun put(key: String, viewModel: ViewModel) {
        viewModelsMap[key] = viewModel
    }

    fun get(key: String): ViewModel {
        return viewModelsMap[key] ?: error("ViewModel instance not found!")
    }

    fun destroy() {
        viewModelsMap.clear()
    }

}


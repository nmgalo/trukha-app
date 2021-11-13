package dev.nmgalo.trukha.ui.library.viewModel.factory

import dev.nmgalo.trukha.ui.library.viewModel.ViewModel
import dev.nmgalo.trukha.ui.library.viewModel.ViewModelStore

class ViewModelProviderFactory : ViewModelStore.Factory {
    override fun <V : ViewModel> create(modelClass: Class<V>): V {
        return modelClass.newInstance()
    }
}

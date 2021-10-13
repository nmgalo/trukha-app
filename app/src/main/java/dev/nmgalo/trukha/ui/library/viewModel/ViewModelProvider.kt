package dev.nmgalo.trukha.ui.library.viewModel

class ViewModelProvider(private val owner: ViewModelStore) {

    fun <T : ViewModel> get(modelClass: Class<T>) {
        val viewModel: ViewModel = owner.get(modelClass.simpleName)
    }

}

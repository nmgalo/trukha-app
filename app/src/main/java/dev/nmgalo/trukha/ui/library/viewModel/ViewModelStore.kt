package dev.nmgalo.trukha.ui.library.viewModel

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import dev.nmgalo.trukha.ui.library.viewModel.factory.ViewModelProviderFactory
import java.util.HashMap

object ViewModelStore : DefaultLifecycleObserver {

    private val viewModelsMap = HashMap<String, ViewModel>()

    fun <T : ViewModel> get(
        lifecycleStoreOwner: LifecycleStoreOwner,
        modelClass: Class<T>,
        factory: Factory = ViewModelProviderFactory()
    ): T {
        lifecycleStoreOwner.lifecycle.addObserver(this)

        val viewModelKey = "ViewModelStore:${modelClass.simpleName}"

        if (viewModelsMap[viewModelKey] == null)
            viewModelsMap[viewModelKey] = factory.create(modelClass)

        return viewModelsMap[viewModelKey] as T
    }

    override fun onDestroy(owner: LifecycleOwner) {
        val lifecycleOwner = owner as LifecycleStoreOwner
        if (!lifecycleOwner.isRecreating()) {
            viewModelsMap.remove("ViewModelStore:${lifecycleOwner.getClassId()}")
        }
    }

    interface Factory {
        fun <V : ViewModel> create(modelClass: Class<V>): V
    }
}

package dev.nmgalo.trukha.ui.library.viewModel

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import java.lang.RuntimeException
import java.util.HashMap

object ViewModelStore : DefaultLifecycleObserver {

    private val viewModelsMap = HashMap<String, ViewModel>()

    private val factory: Factory? = null

    fun <T : ViewModel> get(
        lifecycleStoreOwner: LifecycleStoreOwner,
        modelClass: Class<T>
    ): T {
        lifecycleStoreOwner.lifecycle.addObserver(this)

        val viewModelKey = "ViewModelStore:${modelClass.simpleName}"

        if (viewModelsMap[viewModelKey] == null)
            viewModelsMap[viewModelKey] = modelClass.newInstance()

        return viewModelsMap[viewModelKey] as T
    }

    override fun onDestroy(owner: LifecycleOwner) {
        val lifecycleOwner = owner as LifecycleStoreOwner
        if (!lifecycleOwner.isRecreating()) {
            viewModelsMap.remove("ViewModelStore:" + lifecycleOwner.getClassId())
        }
    }


    class NewInstanceFactory : Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return try {
                modelClass.newInstance()
            } catch (e: InstantiationException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: IllegalAccessException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            }
        }
    }

    interface Factory {
        fun <T : ViewModel?> create(modelClass: Class<T>): T
    }
}

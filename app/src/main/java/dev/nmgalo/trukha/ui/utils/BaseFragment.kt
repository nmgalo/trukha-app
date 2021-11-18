package dev.nmgalo.trukha.ui.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import dev.nmgalo.trukha.App
import dev.nmgalo.trukha.di.AppContainer
import dev.nmgalo.trukha.ui.library.viewModel.LifecycleStoreOwner
import dev.nmgalo.trukha.ui.library.viewModel.ViewModel

abstract class BaseFragment<T : ViewModel>(
    @LayoutRes layoutRes: Int,
) : Fragment(layoutRes),
    LifecycleStoreOwner {

    lateinit var appContainer: AppContainer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        appContainer = (requireActivity().application as App).appContainer
    }

    protected abstract val viewModel: T

    override fun getClassId(): String = viewModel.javaClass.simpleName
    override fun isRecreating() = requireActivity().isChangingConfigurations
}

package dev.nmgalo.trukha.ui.utils

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import dev.nmgalo.trukha.ui.library.viewModel.LifecycleStoreOwner
import dev.nmgalo.trukha.ui.library.viewModel.ViewModel

abstract class BaseFragment<T : ViewModel>(
    @LayoutRes layoutRes: Int,
) : Fragment(layoutRes),
    LifecycleStoreOwner {

    protected abstract val viewModel: T

    override fun getClassId(): String = viewModel.javaClass.simpleName
    override fun isRecreating() = requireActivity().isChangingConfigurations
}

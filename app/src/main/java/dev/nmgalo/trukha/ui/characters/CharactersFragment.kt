package dev.nmgalo.trukha.ui.characters

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import dev.nmgalo.trukha.R
import dev.nmgalo.trukha.databinding.FragmentCharactersBinding
import dev.nmgalo.trukha.ui.library.viewModel.LifecycleStoreOwner
import dev.nmgalo.trukha.ui.library.viewModel.ViewModelStore
import dev.nmgalo.trukha.ui.utils.BaseFragment

class CharactersFragment :
    BaseFragment<CharactersViewModel>(R.layout.fragment_characters),
    LifecycleStoreOwner {

    private val adapter = CharactersRecyclerAdapter()
    override lateinit var viewModel: CharactersViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentCharactersBinding.bind(view).onBind()
        viewModel = ViewModelStore.get(this, CharactersViewModel::class.java)
    }

    private fun FragmentCharactersBinding.onBind() {
        charactersRecycler.layoutManager = LinearLayoutManager(context)
        charactersRecycler.adapter = adapter
        viewModel.charactersObserver.register(adapter::submitList)
    }


    override fun getClassId(): String = viewModel.javaClass.simpleName
    override fun isRecreating() = requireActivity().isChangingConfigurations
}


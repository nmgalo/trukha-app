package dev.nmgalo.trukha.ui.characters

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import dev.nmgalo.trukha.App
import dev.nmgalo.trukha.R
import dev.nmgalo.trukha.databinding.FragmentCharactersBinding
import dev.nmgalo.trukha.ui.library.viewModel.factory.CharactersViewModelProviderFragment
import dev.nmgalo.trukha.ui.library.viewModel.ViewModelStore
import dev.nmgalo.trukha.ui.utils.AdapterScrollListener
import dev.nmgalo.trukha.ui.utils.BaseFragment

class CharactersFragment :
    BaseFragment<CharactersViewModel>(R.layout.fragment_characters) {

    private val adapter = CharactersRecyclerAdapter()
    override lateinit var viewModel: CharactersViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelStore.get(
            this,
            CharactersViewModel::class.java,
            CharactersViewModelProviderFragment(
                (requireActivity().application as App).appContainer.charactersRepository
            )
        )
        FragmentCharactersBinding.bind(view).onBind()
    }

    private fun FragmentCharactersBinding.onBind() {
        val layoutManager = LinearLayoutManager(context)
        charactersRecycler.layoutManager = layoutManager
        charactersRecycler.adapter = adapter
        viewModel.characters.observe(viewLifecycleOwner, adapter::submitList)
        viewModel.fetchNext()

        charactersRecycler.addOnScrollListener(AdapterScrollListener(layoutManager) {
            viewModel.fetchNext()
        })
    }

}


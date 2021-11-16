package dev.nmgalo.trukha.ui.characters

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import dev.nmgalo.trukha.App
import dev.nmgalo.trukha.R
import dev.nmgalo.trukha.databinding.FragmentCharactersBinding
import dev.nmgalo.trukha.ui.library.viewModel.factory.CharactersViewModelFactory
import dev.nmgalo.trukha.ui.library.viewModel.ViewModelStore
import dev.nmgalo.trukha.ui.utils.AdapterScrollListener
import dev.nmgalo.trukha.ui.utils.BaseFragment
import dev.nmgalo.trukha.ui.utils.view.showErrorDialog

class CharactersFragment :
    BaseFragment<CharactersViewModel>(R.layout.fragment_characters) {

    private val adapter = CharactersRecyclerAdapter()
    override lateinit var viewModel: CharactersViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelStore.get(
            this,
            CharactersViewModel::class.java,
            CharactersViewModelFactory(
                (requireActivity().application as App).appContainer.charactersRepository
            ),
        )
        FragmentCharactersBinding.bind(view).onBind()
    }

    private fun FragmentCharactersBinding.onBind() {
        val layoutManager = LinearLayoutManager(context)
        charactersRecycler.layoutManager = layoutManager
        charactersRecycler.adapter = adapter
        viewModel.characters.observe(viewLifecycleOwner,  {
            if (it?.isEmpty() == true) {
                showErrorDialog(message = R.string.characters_not_found)
            }
            adapter.submitList(it)
        })

        charactersRecycler.addOnScrollListener(AdapterScrollListener(layoutManager) {
            viewModel.fetchNext()
        })
    }

}


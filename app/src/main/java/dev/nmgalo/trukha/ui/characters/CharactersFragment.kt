package dev.nmgalo.trukha.ui.characters

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import dev.nmgalo.trukha.R
import dev.nmgalo.trukha.databinding.FragmentCharactersBinding
import dev.nmgalo.trukha.ui.library.viewModel.ViewModelStore
import dev.nmgalo.trukha.ui.utils.BaseFragment

class CharactersFragment :
    BaseFragment<CharactersViewModel>(R.layout.fragment_characters) {

    private val adapter = CharactersRecyclerAdapter()
    override lateinit var viewModel: CharactersViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelStore.get(this, CharactersViewModel::class.java)
        FragmentCharactersBinding.bind(view).onBind()
    }

    private fun FragmentCharactersBinding.onBind() {
        charactersRecycler.layoutManager = LinearLayoutManager(context)
        charactersRecycler.adapter = adapter
        viewModel.characters.registerObserver(adapter::submitList)
        viewModel.getCharacters()
    }

}


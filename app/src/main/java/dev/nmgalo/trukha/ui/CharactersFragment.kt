package dev.nmgalo.trukha.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dev.nmgalo.trukha.R

class CharactersFragment : Fragment(R.layout.fragment_characters) {

    private val sharedPreferences = context?.getSharedPreferences("USER_DATE", Context.MODE_PRIVATE)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences?.getString("ACCESS_TOKEN", "")
    }

}

package dev.nmgalo.trukha.ui.characters.model

import dev.nmgalo.trukha.databinding.CharacterItemBinding

data class CharactersUIModel(
    val id: Long,
    val name: String
) {
    fun bindTo(binding: CharacterItemBinding) {
        binding.characterName.text = name
    }
}

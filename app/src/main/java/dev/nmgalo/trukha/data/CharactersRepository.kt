package dev.nmgalo.trukha.data

import dev.nmgalo.trukha.data.model.characters.CharacterContentDTO

interface CharactersRepository {
    fun get(pageNumber: Int): List<CharacterContentDTO>
}

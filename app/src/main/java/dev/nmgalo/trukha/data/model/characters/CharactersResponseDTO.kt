package dev.nmgalo.trukha.data.model.characters

import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponseDTO(
    val content: List<CharacterContentDTO>,
    val totalCount: Int,
    val offset: Int,
    val isLast: Boolean
)

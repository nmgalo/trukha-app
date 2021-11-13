package dev.nmgalo.trukha.data.model.characters

import dev.nmgalo.trukha.ui.characters.model.CharactersUIModel
import kotlinx.serialization.Serializable

@Serializable
data class CharacterContentDTO(
    val biography: CharacterBiography,
    val id: Int,
    val image: CharacterImage
) {
    fun toUIModel() = CharactersUIModel(
        id = this.id,
        name = this.biography.fullName,
        imageURL = this.image.url
    )
}

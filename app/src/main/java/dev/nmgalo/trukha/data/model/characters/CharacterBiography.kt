package dev.nmgalo.trukha.data.model.characters

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterBiography(
    @SerialName("full-name")
    val fullName: String
)

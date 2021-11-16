package dev.nmgalo.trukha.data

import dev.nmgalo.trukha.data.model.characters.CharacterContentDTO
import dev.nmgalo.trukha.data.model.characters.CharactersResponseDTO
import dev.nmgalo.trukha.ui.utils.CommonRequestResult
import dev.nmgalo.trukha.ui.utils.json.JsonHelper

class CharactersRepositoryImpl(
    private val service: ApiClient,
    private val jsonHelper: JsonHelper
) : CharactersRepository {
    override fun get(pageNumber: Int): List<CharacterContentDTO> {
        return when (val response = service.getCharacters(pageNumber)) {
            is CommonRequestResult.OnSuccess -> {
                jsonHelper.decodeResult<CharactersResponseDTO>(response.result).content
            }
            else -> emptyList()
        }
    }
}

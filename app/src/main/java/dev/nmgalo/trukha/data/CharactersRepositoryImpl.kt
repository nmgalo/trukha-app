package dev.nmgalo.trukha.data

class CharactersRepositoryImpl : CharactersRepository {
    override fun get(): List<String> {
        return listOf("Nick", "Jemala", "Zaura")
    }
}

package dev.nmgalo.trukha.ui.utils

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

object JsonHelper {

    val json = Json {
        ignoreUnknownKeys = true
    }

    @ExperimentalSerializationApi
    inline fun <reified T> decodeResult(response: String): T {
        return json.decodeFromString(response)
    }
}

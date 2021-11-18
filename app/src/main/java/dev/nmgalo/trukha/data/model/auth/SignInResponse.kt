package dev.nmgalo.trukha.data.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class SignInResponse(
    val userId: Int,
    val userName: String,
    val name: String,
    val accessToken: String
)

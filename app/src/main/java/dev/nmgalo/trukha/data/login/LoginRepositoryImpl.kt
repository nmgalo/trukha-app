package dev.nmgalo.trukha.data.login

import dev.nmgalo.trukha.data.ApiClient
import dev.nmgalo.trukha.data.model.auth.SignInResponse
import dev.nmgalo.trukha.di.SharedPreferenceModule
import dev.nmgalo.trukha.ui.login.UserCredentials
import dev.nmgalo.trukha.ui.utils.CommonRequestResult
import dev.nmgalo.trukha.ui.utils.json.JsonHelper

class LoginRepositoryImpl(
    private val apiClient: ApiClient,
    private val jsonHelper: JsonHelper,
    private val sharedPreferenceModule: SharedPreferenceModule
) : LoginRepository {
    override fun auth(userCredentials: UserCredentials) {
        when (val response = apiClient.auth(userCredentials)) {
            is CommonRequestResult.OnSuccess -> {
                val token = jsonHelper.decodeResult<SignInResponse>(response.result)
                sharedPreferenceModule.putToken(token.accessToken)
            }
            else -> throw UserNotFoundException()
        }
    }
}

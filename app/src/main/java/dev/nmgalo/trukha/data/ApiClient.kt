package dev.nmgalo.trukha.data

import dev.nmgalo.trukha.BuildConfig
import dev.nmgalo.trukha.ui.login.UserCredentials
import dev.nmgalo.trukha.ui.utils.CommonRequestResult
import okhttp3.*
import java.lang.Exception
import java.util.concurrent.TimeUnit

object ApiClient {

    private fun httpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    fun auth(userCredentials: UserCredentials): CommonRequestResult {
        val formData = FormBody.Builder().add("username", userCredentials.userName)
            .add("password", userCredentials.password)
        try {
            val response: Response = httpClient().newCall(
                Request.Builder().url("${BuildConfig.BASE_URL}auth/login")
                    .post(formData.build()).build()
            ).execute()
            if (response.isSuccessful)
                return CommonRequestResult.OnSuccess(response.body?.string().toString())
        } catch (_: Exception) {
            return CommonRequestResult.OnError
        }
        return CommonRequestResult.OnError
    }

    fun getCharacters(): CommonRequestResult {
        try {
            val response = httpClient().newCall(
                Request.Builder()
                    .url("${BuildConfig.BASE_URL}/comics/list-characters?offset=1&pageSize=20")
                    .get().build()
            ).execute()
            if (response.isSuccessful)
                return CommonRequestResult.OnSuccess(response.body?.string().toString())
        } catch (_: Exception) {
            return CommonRequestResult.OnError
        }
        return CommonRequestResult.OnError
    }

}

package dev.nmgalo.trukha.data

import dev.nmgalo.trukha.ui.login.UserCredentials
import dev.nmgalo.trukha.ui.utils.CommonRequestResult
import okhttp3.*
import java.util.concurrent.TimeUnit

class ApiClient {

    private fun httpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    fun auth(userCredentials: UserCredentials): CommonRequestResult {
        val formData = FormBody.Builder().add("username", userCredentials.userName)
            .add("password", userCredentials.password)
        val response: Response = httpClient().newCall(
            Request.Builder().url("${BASE_URL}auth/login")
                .post(formData.build()).build()
        ).execute()
        if (response.isSuccessful)
            return CommonRequestResult.OnSuccess(response.body?.string().toString())
        return CommonRequestResult.OnError
    }

    companion object {
        const val BASE_URL = "https://commschool-android-api.herokuapp.com/"
    }

}

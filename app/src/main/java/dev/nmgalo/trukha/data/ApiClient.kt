package dev.nmgalo.trukha.data

import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

class ApiClient {

    private fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private fun request(path: String) = Request.Builder().url("$BASE_URL$path")
        .post(
            FormBody.Builder()
                .add("username", "asdasd")
                .add("password", "asdasd")
                .build()
        ).build()

    fun get(path: String) = provideOkHttpClient().newCall(request(path))

    companion object {
        const val BASE_URL = "https://commschool-android-api.herokuapp.com/"
    }

}

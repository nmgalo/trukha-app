package dev.nmgalo.trukha.data

import okhttp3.*
import java.util.concurrent.TimeUnit

class ApiClient {

    private fun httpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    fun postData(endpoint: String, data: HashMap<String, String>): Response {

        val formData = FormBody.Builder().apply {
            data.map {
                this.add(it.key, it.value)
            }
        }
        return httpClient().newCall(
            Request.Builder().url("${BASE_URL}$endpoint")
                .post(formData.build()).build()
        ).execute()
    }

    companion object {
        const val BASE_URL = "https://commschool-android-api.herokuapp.com/"
    }

}

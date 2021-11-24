package dev.nmgalo.trukha.data

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class HttpLoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(request).apply {
            Log.d(
                "HttpLoggingInterceptor",
                "${if (this.isSuccessful) "\uD83D\uDFE2" else "\uD83D\uDD34"} ${this.code} ${request.method} " +
                        " -> ${request.url}" +
                        " -> HEADERS: ${request.headers.map { "${it.first}=${it.second}" }}"
            )
        }
    }
}

package dev.nmgalo.trukha.data

import okhttp3.Interceptor
import okhttp3.Response

class HttpLoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        println("🔴 ${request.method} -> URL: ${request.url} -> HEADERS: ${request.headers.map { "${it.first}=${it.second}" }}")
        return chain.proceed(request)
    }
}

package dev.nmgalo.trukha.data

import okhttp3.Interceptor
import okhttp3.Response

class HttpLoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        println("""
            
            ⚪ ${chain.request().method}
                        URL: ${chain.request().url}
                        BODY: ${chain.request().body}
                        
        """.trimIndent())
        return chain.proceed(chain.request())
    }

}

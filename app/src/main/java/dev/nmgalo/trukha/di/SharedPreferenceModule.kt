package dev.nmgalo.trukha.di

import android.content.Context

class SharedPreferenceModule(context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun putToken(token: String) = sharedPreferences.edit().putString(ACCESS_TOKEN, token).apply()

    fun getToken(): String = sharedPreferences.getString(ACCESS_TOKEN, "") ?: ""

    fun delete() = sharedPreferences.edit().clear().apply()

    companion object {
        const val SHARED_PREFERENCE_NAME = "USER_DATA"
        const val ACCESS_TOKEN = "ACCESS_TOKEN"
    }

}

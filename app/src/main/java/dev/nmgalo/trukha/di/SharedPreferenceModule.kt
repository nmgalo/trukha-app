package dev.nmgalo.trukha.di

import android.content.SharedPreferences

class SharedPreferenceModule(private val sharedPreferences: SharedPreferences) {

    fun putToken(token: String) = sharedPreferences.edit().putString(ACCESS_TOKEN, token).apply()

    fun getToken(): String = sharedPreferences.getString(ACCESS_TOKEN, "") ?: ""

    fun delete() = sharedPreferences.edit().clear().apply()

    companion object {
        const val ACCESS_TOKEN = "ACCESS_TOKEN"
    }

}

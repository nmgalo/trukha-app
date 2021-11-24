package dev.nmgalo.trukha

import android.app.Application
import dev.nmgalo.trukha.di.AppContainer

class App : Application() {
    lateinit var appContainer: AppContainer
    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(applicationContext)
    }
}

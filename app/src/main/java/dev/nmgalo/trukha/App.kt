package dev.nmgalo.trukha

import android.app.Application
import dev.nmgalo.trukha.di.AppContainer

class App : Application() {
    val appContainer = AppContainer()
}

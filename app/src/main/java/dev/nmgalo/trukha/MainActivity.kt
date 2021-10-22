package dev.nmgalo.trukha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import dev.nmgalo.trukha.di.AppContainer
import dev.nmgalo.trukha.ui.characters.CharactersFragment
import dev.nmgalo.trukha.ui.login.LoginFragment

class MainActivity : AppCompatActivity() {

    lateinit var container: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = App().appContainer

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add<CharactersFragment>(R.id.container)
            }
        }
    }
}

package dev.nmgalo.trukha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import dev.nmgalo.trukha.di.AppContainer
import dev.nmgalo.trukha.ui.CharactersFragment
import dev.nmgalo.trukha.ui.SignInFragment

class MainActivity : AppCompatActivity() {

    lateinit var container: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = App().appContainer

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace<SignInFragment>(R.id.container)
                addToBackStack("characters")
                replace<CharactersFragment>(R.id.container, "characters")
            }
        }
    }

//    private fun get() {
//        val runnable = Runnable {
//            ApiClient().get().enqueue(object : Callback {
//                override fun onFailure(call: Call, e: IOException) {
//                    e.printStackTrace()
//                }
//
//                override fun onResponse(call: Call, response: Response) {
//                    response.body
//                }
//            })
//        }
//        Thread(runnable).start()
//    }

}

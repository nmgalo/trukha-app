package dev.nmgalo.trukha.ui.login

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import dev.nmgalo.trukha.R
import dev.nmgalo.trukha.data.ApiClient
import dev.nmgalo.trukha.data.ApiService
import dev.nmgalo.trukha.data.model.SignInResponse
import dev.nmgalo.trukha.ui.CharactersFragment
import dev.nmgalo.trukha.ui.utils.showErrorDialog
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val editor = context?.getSharedPreferences("USER_DATE", Context.MODE_PRIVATE)?.edit()

    private lateinit var loader: ProgressBar
    private lateinit var loginButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginButton = view.findViewById(R.id.submit)
        loginButton.setOnClickListener {
            it.isClickable = false
            loader = view.findViewById(R.id.loader)
            loader.visibility = View.VISIBLE
            login(
                userName = view.findViewById<EditText>(R.id.userNameEditText).text.toString(),
                password = view.findViewById<EditText>(R.id.userPasswordEditText).text.toString()
            )
        }
    }

    private val json = Json {
        ignoreUnknownKeys = true
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun login(userName: String, password: String) {
        Thread {
            val result = ApiClient().postData(
                ApiService.LOGIN,
                hashMapOf(
                    "username" to userName,
                    "password" to password
                )
            )
            if (result.isSuccessful) {
                val response = json.decodeFromString<SignInResponse>(
                    result.body?.string() ?: error("Serialization error occurred")
                )
                editor?.putString("ACCESS_TOKEN", response.accessToken)
                parentFragmentManager.commit {
                    addToBackStack(null)
                    replace<CharactersFragment>(R.id.container)
                }
            } else {
                activity?.runOnUiThread {
                    loader.visibility = View.GONE
                    loginButton.isClickable = true
                    context?.showErrorDialog(message = R.string.user_not_found)
                }
            }
        }.start()
    }

}

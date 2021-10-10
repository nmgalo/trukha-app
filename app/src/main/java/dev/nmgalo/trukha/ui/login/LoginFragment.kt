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
import dev.nmgalo.trukha.data.model.SignInResponse
import dev.nmgalo.trukha.ui.CharactersFragment
import dev.nmgalo.trukha.ui.utils.CommonRequestResult
import dev.nmgalo.trukha.ui.utils.JsonHelper
import dev.nmgalo.trukha.ui.utils.showErrorDialog
import kotlinx.serialization.ExperimentalSerializationApi

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val editor = context?.getSharedPreferences("USER_DATE", Context.MODE_PRIVATE)?.edit()

    private lateinit var loader: ProgressBar
    private lateinit var loginButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginButton = view.findViewById(R.id.submit)
        loader = view.findViewById(R.id.loader)
        loginButton.setOnClickListener {
            it.isClickable = false
            loader.visibility = View.VISIBLE
            login(
                userName = view.findViewById<EditText>(R.id.userNameEditText).text.toString(),
                password = view.findViewById<EditText>(R.id.userPasswordEditText).text.toString()
            )
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun login(userName: String, password: String) {
        Thread {
            when (val response = ApiClient().auth(UserCredentials(userName, password))) {
                is CommonRequestResult.OnSuccess -> {
                    JsonHelper.decodeResult<SignInResponse>(response.result).run {
                        editor?.putString("ACCESS_TOKEN", this.accessToken)
                        parentFragmentManager.commit {
                            addToBackStack(null)
                            replace<CharactersFragment>(R.id.container)
                        }
                    }
                }
                is CommonRequestResult.OnError -> {
                    activity?.runOnUiThread {
                        loader.visibility = View.GONE
                        loginButton.isClickable = true
                        showErrorDialog(message = R.string.user_not_found)
                    }
                }
            }
        }.start()
    }

}

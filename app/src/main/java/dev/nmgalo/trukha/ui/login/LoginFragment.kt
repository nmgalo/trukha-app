package dev.nmgalo.trukha.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import dev.nmgalo.trukha.R
import dev.nmgalo.trukha.data.model.auth.SignInResponse
import dev.nmgalo.trukha.di.SharedPreferenceModule
import dev.nmgalo.trukha.ui.characters.CharactersFragment
import dev.nmgalo.trukha.ui.library.viewModel.LifecycleStoreOwner
import dev.nmgalo.trukha.ui.library.viewModel.ViewModelStore
import dev.nmgalo.trukha.ui.utils.CommonRequestResult
import dev.nmgalo.trukha.ui.utils.json.JsonHelper
import dev.nmgalo.trukha.ui.utils.view.showErrorDialog

class LoginFragment : Fragment(R.layout.fragment_login), LifecycleStoreOwner {

    private lateinit var loader: ProgressBar
    private lateinit var loginButton: Button
    private lateinit var viewModel: LoginViewModel
    private lateinit var userName: EditText
    private lateinit var password: EditText

    private lateinit var sharedPreference: SharedPreferenceModule

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelStore.get(this, LoginViewModel::class.java)

        sharedPreference = SharedPreferenceModule(requireContext())

        loginButton = view.findViewById(R.id.submit)
        loader = view.findViewById(R.id.loader)
        userName = view.findViewById(R.id.userNameEditText)
        password = view.findViewById(R.id.userPasswordEditText)

        loginButton.setOnClickListener {
            it.isClickable = false
            loader.visibility = View.VISIBLE
            Thread {
                when (val response = viewModel.signIn(
                    UserCredentials(
                        userName.text.toString(),
                        password.text.toString()
                    )
                )) {
                    is CommonRequestResult.OnSuccess -> {
                        val result = JsonHelper.decodeResult<SignInResponse>(response.result)
                        sharedPreference.putToken(result.accessToken)
                        parentFragmentManager.commit {
                            addToBackStack(null)
                            replace<CharactersFragment>(R.id.container)
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

    override fun getClassId(): String = "LoginViewModel"

    override fun isRecreating() = requireActivity().isChangingConfigurations

}

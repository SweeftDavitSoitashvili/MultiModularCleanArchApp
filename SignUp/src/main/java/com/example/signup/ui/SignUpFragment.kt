package com.example.signup.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.authdomain.interactors.UserUseCase
import com.example.authdomain.models.User
import com.example.multimoduleapp.di.ModuleManager
import com.example.multimoduleapp.navigation.Navigation
import com.example.multimoduleapp.navigation.NavigationImpl
import com.example.signup.R
import com.example.signup.ui.vm.SignUpVm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

class SignUpFragment : Fragment() {

    private val loadScopedModules by inject<ModuleManager>()

    private val signUpModule = module {
        single(qualifier = named<SignUpFragment>()) { UserUseCase(get()) }
        viewModel { SignUpVm(get(qualifier = named<SignUpFragment>())) }
    }

    private val signUpVm: SignUpVm by inject()

    private lateinit var navigation: Navigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadScopedModules.addModule(signUpModule)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigation = NavigationImpl(requireView().findNavController())
        signUp()
        navigateToSignIn()
    }

    private fun signUp() {
        val inputEmailInput: EditText = requireView().findViewById(R.id.emailAddressInput)
        val inputPasswordText: EditText = requireView().findViewById(R.id.passwordInput)
        val inputRepeatPassword: EditText = requireView().findViewById(R.id.repeatPasswordInput)
        val signUpBtn: Button = requireView().findViewById(R.id.signUpBtn)

        signUpBtn.setOnClickListener {
            val email = inputEmailInput.text.toString()
            val password = inputPasswordText.text.toString()
            val repeatPassword = inputRepeatPassword.text.toString()

            if (!isPasswordMatch(password, repeatPassword)) {
                Toast.makeText(it.context, "Password mismatch", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }


            CoroutineScope(Dispatchers.Main).launch {

                if (signUpVm.isUserExist(email)) {
                    Toast.makeText(it.context, "User Already exists", Toast.LENGTH_LONG).show()
                    return@launch
                }

                signUpVm.saveUser(
                    User(
                        email,
                        password
                    )
                )
                Toast.makeText(it.context, "You have signed up successfully", Toast.LENGTH_LONG).show()
                navigation.navigateToSignIn()
            }
        }
    }

    private fun navigateToSignIn() {
        requireView().findViewById<Button>(R.id.signInBtn).setOnClickListener {
            navigation.navigateToSignIn()
        }
    }

    private fun isPasswordMatch(password: String, repeatPassword: String) =
        password == repeatPassword

    override fun onDestroy() {
        super.onDestroy()
        loadScopedModules.removeModule(signUpModule)
    }
}
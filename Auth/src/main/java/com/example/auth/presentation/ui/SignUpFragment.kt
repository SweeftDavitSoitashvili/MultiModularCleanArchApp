package com.example.auth.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.auth.R
import com.example.auth.domain.interactors.UserUseCase
import com.example.auth.presentation.validators.EmptyFieldException
import com.example.auth.presentation.vm.SignInVm
import com.example.auth.presentation.vm.SignUpVm
import com.example.authdomain.models.User
import com.example.base.ui.fragments.BaseFeatureFragment
import com.example.navigation.Navigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

class SignUpFragment : BaseFeatureFragment<SignUpVm>() {

    override val layout: Int = R.layout.fragment_sign_up

    override val vm: SignUpVm by inject()

    override val navigator: Navigator by inject()

    override fun diModules() = module {
        factory(qualifier = named<SignUpFragment>()) { UserUseCase(get()) }
        viewModel {SignUpVm(get(qualifier = named<SignUpFragment>()))}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

            try {
                checkOnEmptyFields(email,password,repeatPassword)
            } catch (exception : EmptyFieldException) {
                Toast.makeText(it.context, "Please fill all fields", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (!isPasswordMatch(password, repeatPassword)) {
                Toast.makeText(it.context, "Password mismatch", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            CoroutineScope(Dispatchers.Main).launch {

                if (vm.isUserExist(email)) {
                    Toast.makeText(it.context, "User Already exists", Toast.LENGTH_LONG).show()
                    return@launch
                }

                vm.saveUser(
                    User(
                        email,
                        password
                    )
                )
                Toast.makeText(it.context, "You have signed up successfully", Toast.LENGTH_LONG).show()
                navigator.navController?.navigate(R.id.action_signUpFragment_to_signInFragment)
            }
        }
    }

    private fun navigateToSignIn() {
        requireView().findViewById<Button>(R.id.signInBtn).setOnClickListener {
            navigator.navController?.navigate(R.id.action_signUpFragment_to_signInFragment)
        }
    }

    private fun isPasswordMatch(password: String, repeatPassword: String) =
        password == repeatPassword

    private fun checkOnEmptyFields(email : String, password: String, repeatPassword: String) {
        if (email.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
            throw EmptyFieldException()
        }
    }
}
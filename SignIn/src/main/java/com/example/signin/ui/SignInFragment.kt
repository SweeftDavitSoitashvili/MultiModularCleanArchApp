package com.example.signin.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.authdomain.interactors.UserUseCase
import com.example.base.ui.fragments.BaseFeatureFragment
import com.example.multimoduleapp.navigation.NavigationImpl
import com.example.signin.R
import com.example.signin.ui.validator.EmptyFieldException
import com.example.signin.ui.vm.SignInVm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

class SignInFragment : BaseFeatureFragment<SignInVm>() {

    override val vm: SignInVm by inject()

    override val layout: Int = R.layout.fragment_sign_in

    override fun diModule() = module {
        single(qualifier = named<SignInFragment>()) { UserUseCase(get()) }
        viewModel { SignInVm(get(qualifier = named<SignInFragment>())) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigation = NavigationImpl(requireView().findNavController())
        signIn()
        navigateToSignUp()
    }

    private fun signIn() {
        with(requireView()) {
            val inputEmailInput = findViewById<EditText>(R.id.emailAddressInput)
            val inputPasswordInput = findViewById<EditText>(R.id.passwordInput)
            val signInBtn = findViewById<Button>(R.id.signInBtn)

            signInBtn.setOnClickListener {
                val email = inputEmailInput.text.toString()
                val password = inputPasswordInput.text.toString()

                try {
                    checkOnEmptyFields(email,password)
                } catch (exception : EmptyFieldException) {
                    Toast.makeText(it.context, "Please fill all fields", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }

                CoroutineScope(Dispatchers.Main).launch {
                    if (!isUserExist(email, password))  {
                        Toast.makeText(it.context, "User does not exist", Toast.LENGTH_LONG).show()
                        return@launch
                    }

                    if (vm.isUserEmailExist(email)) {
                        Toast.makeText(it.context, "You signed in successfully", Toast.LENGTH_LONG).show()
                        navigation.navigateToDashboard()
                    } else {
                        Toast.makeText(it.context, "User not found, try again", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun checkOnEmptyFields(email : String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            throw EmptyFieldException()
        }
    }

    private fun navigateToSignUp() {
        requireView().findViewById<Button>(R.id.signUpBtn).setOnClickListener {
            navigation.navigateToSignUp()
        }
    }

    private suspend fun isUserExist(email : String, password : String) = vm.isUserExist(email,password)

}
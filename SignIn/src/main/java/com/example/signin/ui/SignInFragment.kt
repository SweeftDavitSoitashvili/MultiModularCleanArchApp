package com.example.signin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.multimoduleapp.navigation.Navigation
import com.example.multimoduleapp.navigation.NavigationImpl
import com.example.signin.R
import com.example.signin.ui.vm.SignInVm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInFragment : Fragment() {

    private val signInVm: SignInVm by lazy {
        ViewModelProvider(this).get(SignInVm::class.java)
    }

    private lateinit var navigation: Navigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
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

                CoroutineScope(Dispatchers.Main).launch {
                    if (!isUserExist(inputEmailInput.text.toString(), inputPasswordInput.text.toString())) {
                        Toast.makeText(it.context, "User does not exist", Toast.LENGTH_LONG).show()
                    }
                    if (signInVm.isUserEmailExist(inputEmailInput.text.toString())) {
                        Toast.makeText(it.context, "You signed in successfully", Toast.LENGTH_LONG).show()
                        navigation.navigateToDashboard()
                    } else {
                        Toast.makeText(it.context, "User not found, try again", Toast.LENGTH_LONG).show()
                    }
                }
            }

        }
    }

    private fun navigateToSignUp() {
        requireView().findViewById<Button>(R.id.signUpBtn).setOnClickListener {
            navigation.navigateToSignUp()
        }
    }

    private suspend fun isUserExist(email : String, password : String) = signInVm.isUserExist(email,password)

}
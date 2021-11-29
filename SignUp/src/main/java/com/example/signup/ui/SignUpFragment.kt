package com.example.signup.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.authdomain.models.User
import com.example.multimoduleapp.navigation.Navigation
import com.example.multimoduleapp.navigation.NavigationImpl
import com.example.signup.R
import com.example.signup.ui.vm.SignUpVm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpFragment : Fragment() {

    private val signUpVm: SignUpVm by lazy {
        ViewModelProvider(this).get(SignUpVm::class.java)
    }

    private lateinit var navigation: Navigation

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
        val signUpBtn: Button = requireView().findViewById(R.id.signUpBtn)

        signUpBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                signUpVm.saveUser(
                    User(
                        2,
                        inputEmailInput.text.toString(),
                        inputPasswordText.text.toString()
                    )
                )
            }
        }
    }

    private fun navigateToSignIn() {
        requireView().findViewById<Button>(R.id.signInBtn).setOnClickListener {
            navigation.navigateToSignIn()
        }
    }
}
package com.example.signin.ui.vm

import androidx.lifecycle.ViewModel
import com.example.authdomain.interactors.UserUseCase
import com.example.authdomain.models.User
import com.example.authdomain.repository.UserRepository
import org.koin.java.KoinJavaComponent.inject


class SignInVm(private val userUseCase: UserUseCase) : ViewModel() {

    suspend fun getUser(email : String) = userUseCase.getUserByEmail(email)
    suspend fun saveUser(user : User) {
        userUseCase.saveUser(user)
    }
    suspend fun isUserEmailExist(email : String) = userUseCase.isUserEmailExists(email)

    suspend fun isUserExist(email : String, password : String) = userUseCase.isUserExist(email,password)
}
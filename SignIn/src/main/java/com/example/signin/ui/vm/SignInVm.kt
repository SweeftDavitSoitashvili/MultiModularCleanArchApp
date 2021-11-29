package com.example.signin.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.authdomain.interactors.UserUseCase
import com.example.authdomain.models.User
import com.example.authdomain.repository.UserRepository
import org.koin.java.KoinJavaComponent.inject


class SignInVm : ViewModel() {

    private val userRepository : UserRepository by inject(UserRepository::class.java)
    private val userUseCase: UserUseCase by lazy {
        UserUseCase(userRepository)
    }
    suspend fun getUser(email : String) = userUseCase.getUserByEmail(email)
    suspend fun saveUser(user : User) {
        userUseCase.saveUser(user)
    }
    suspend fun isUserExist(email : String) = userUseCase.isUserExists(email)
}
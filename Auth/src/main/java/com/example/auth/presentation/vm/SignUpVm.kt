package com.example.auth.presentation.vm

import androidx.lifecycle.ViewModel
import com.example.auth.domain.interactors.UserUseCase
import com.example.authdomain.models.User

class SignUpVm(
    private val userUseCase: UserUseCase
) : ViewModel() {

    suspend fun getUser(email : String) = userUseCase.getUserByEmail(email)

    suspend fun saveUser(user : User) {
        userUseCase.saveUser(user)
    }
    suspend fun isUserExist(email : String) = userUseCase.isUserEmailExists(email)
}
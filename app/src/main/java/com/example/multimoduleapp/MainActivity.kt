package com.example.multimoduleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import com.example.authdomain.models.User
import com.example.authdomain.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class MainActivity : AppCompatActivity() {
    private val userRepository : UserRepository by inject(UserRepository::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            userRepository.saveUser(User(1, "Test User", "123123"))
            d("asdadsadsaa", userRepository.isUserExist("Test User").toString())
        }
    }
}
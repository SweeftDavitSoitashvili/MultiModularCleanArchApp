package com.example.multimoduleapp

import android.app.Application
import com.example.authdata.data_source.user.UserDataSource
import com.example.authdata.data_source.user.UserDataSourceImpl
import com.example.authdata.mappers.UserMapper
import com.example.authdata.repository.user.UserRepositoryImpl
import com.example.authdata.room_db.AppDatabase
import com.example.authdomain.repository.UserRepository

import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

class MainApp : Application() {
    private lateinit var koinModules : KoinApplication

    companion object {
        private var mainApp: MainApp? = null

        fun getMainApp() = mainApp
    }

    override fun onCreate() {
        super.onCreate()
        if (mainApp == null) {
            mainApp = this
        }
        initKoin()

    }

    private fun initKoin() {
        startKoin {
            koinModules = modules(initModules())
        }
    }

    private fun initModules(): List<Module> {
        val modules = mutableListOf<Module>()
        val globalModules = module {
            single { AppDatabase.getDatabase(MainApp.mainApp!!.applicationContext) }
            single<UserDataSource> { UserDataSourceImpl(get()) }
            single { UserMapper() }
            single<UserRepository> { UserRepositoryImpl(get(), get()) }
        }

        modules.add(globalModules)
        return modules

    }

}
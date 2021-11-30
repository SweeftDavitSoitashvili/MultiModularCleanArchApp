package com.example.multimoduleapp

import android.app.Application
import com.example.authdata.data_source.user.UserDataSource
import com.example.authdata.data_source.user.UserDataSourceImpl
import com.example.authdata.mappers.UserMapper
import com.example.authdata.repository.user.UserRepositoryImpl
import com.example.authdata.room_db.AppDatabase
import com.example.authdomain.repository.UserRepository
import com.example.dashboarddata.RetrofitClient
import com.example.dashboarddata.data_sources.resource.ResourceDataSource
import com.example.dashboarddata.data_sources.resource.ResourceDataSourceImpl
import com.example.dashboarddata.mappers.resource.ResourceMapper
import com.example.dashboarddata.remote_service.RemoteApiService
import com.example.dashboarddata.repository.ResourceRepositoryImpl
import com.example.dashboarddomain.repository.ResourceRepository
import com.example.multimoduleapp.di.ModuleManager

import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

class MainApp : Application(), ModuleManager {
    private lateinit var koinApplication: KoinApplication

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
        koinApplication = startKoin {
            loadKoinModules(initGlobalModules())
        }
    }

    override fun addModule(featureModule: Module) {
        koinApplication.modules(featureModule)
    }

    override fun removeModule(featureModule: Module) {
        koinApplication.unloadModules(featureModule)
    }

    private fun initGlobalModules(): List<Module> {
        val modules = mutableListOf<Module>()
        val authModule = module {
            single { AppDatabase.getDatabase(MainApp.mainApp!!.applicationContext) }
            single<UserDataSource> { UserDataSourceImpl(get()) }
            single { UserMapper() }
            single<UserRepository> { UserRepositoryImpl(get(), get()) }
        }

        val dashboardModule = module {
            single { RetrofitClient.retrofit.create(RemoteApiService::class.java) }
            single<ResourceDataSource> { ResourceDataSourceImpl(get()) }
            single { ResourceMapper() }
            single<ResourceRepository> { ResourceRepositoryImpl(get(), get()) }
        }

        modules.add(authModule)
        modules.add(dashboardModule)
        modules.add(module {
            single<ModuleManager> { mainApp!! }
        })

        return modules
    }

    override fun onTerminate() {
        super.onTerminate()
        koinApplication.close()
    }

}
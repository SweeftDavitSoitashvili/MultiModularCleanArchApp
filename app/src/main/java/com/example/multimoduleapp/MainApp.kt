package com.example.multimoduleapp

import android.app.Application
import com.example.auth.data.data_source.user.UserDataSource
import com.example.auth.data.data_source.user.UserDataSourceImpl
import com.example.authdata.mappers.UserMapper
import com.example.authdata.repository.user.UserRepositoryImpl
import com.example.authdata.room_db.AppDatabase
import com.example.authdomain.repository.UserRepository
import com.example.dashboard.data.RetrofitClient
import com.example.dashboard.data.data_sources.resource.ResourceDataSource
import com.example.dashboard.data.data_sources.resource.ResourceDataSourceImpl
import com.example.dashboard.data.mappers.resource.ResourceMapper
import com.example.dashboard.data.remote_service.RemoteApiService
import com.example.dashboard.data.repository.ResourceRepositoryImpl
import com.example.dashboard.domain.repository.ResourceRepository
import com.example.di.FeatureModuleManager
import com.example.navigation.Navigator
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

class MainApp : Application(), FeatureModuleManager {
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
            modules(getModules())
        }
    }

    private fun getModules(): List<Module> {
        val modules = mutableListOf<Module>()

        modules.add(module {
            single { Navigator() }
            single<FeatureModuleManager> { mainApp!! }
        })
        modules.add(getAuthModule())
        modules.add(getDashboardModule())

        return modules
    }

    private fun getAuthModule() = module {
        single { AppDatabase.getDatabase(mainApp!!.applicationContext) }
        single<UserDataSource> { UserDataSourceImpl(get()) }
        single { UserMapper() }
        single<UserRepository> { UserRepositoryImpl(get(), get()) }
    }

    private fun getDashboardModule() = module {
        single { RetrofitClient.retrofit.create(RemoteApiService::class.java) }
        single<ResourceDataSource> { ResourceDataSourceImpl(get()) }
        single { ResourceMapper() }
        single<ResourceRepository> { ResourceRepositoryImpl(get(), get()) }
    }

    override fun onTerminate() {
        super.onTerminate()
        koinApplication.close()
    }

    override fun loadModule(module: Module) {
        koinApplication.modules(module)
    }

    override fun unLoadModule(module: Module) {
        koinApplication.unloadModules(module)
    }
}
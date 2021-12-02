package com.example.multimoduleapp

import android.app.Application
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
            loadKoinModules(getModules())
        }
    }

    override fun addModule(featureModule: Module) {
        koinApplication.modules(featureModule)
    }

    override fun removeModule(featureModule: Module) {
        koinApplication.unloadModules(featureModule)
    }

    override fun getGlobalModules(modules: List<Module>) {
        koinApplication.modules(modules)
    }

    private fun getModules(): List<Module> {
        val modules = mutableListOf<Module>()

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
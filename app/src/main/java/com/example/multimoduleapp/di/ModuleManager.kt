package com.example.multimoduleapp.di

import org.koin.core.module.Module

interface ModuleManager {
    fun addModule(featureModule : Module)
    fun removeModule(featureModule: Module)
}
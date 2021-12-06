package com.example.di

import org.koin.core.module.Module

interface FeatureModuleManager {
    fun loadModule(module : Module)
    fun unLoadModule(module: Module)
}
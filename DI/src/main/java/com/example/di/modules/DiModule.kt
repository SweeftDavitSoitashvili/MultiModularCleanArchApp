package com.example.di.modules

import android.content.Context
import com.example.authdata.data_source.user.UserDataSource
import com.example.authdata.data_source.user.UserDataSourceImpl
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
import com.example.dashboarddomain.repository.ResourceRepository
import org.koin.core.module.Module
import org.koin.dsl.module

class DiModule(private val context : Context) {
    fun getGlobalModules() : List<Module> {
        val modules = mutableListOf<Module>()
        val authModule = module {
            single { AppDatabase.getDatabase(context) }
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

        return modules
    }
}
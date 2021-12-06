package com.example.base.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.di.FeatureModuleManager
import com.example.navigation.Navigator
import org.koin.android.ext.android.inject
import org.koin.core.module.Module

abstract class BaseFeatureFragment<VM : ViewModel> : Fragment() {

    protected abstract val vm : VM

    protected abstract val layout : Int

    protected abstract val navigator : Navigator

    protected abstract fun diModules() : Module

    private val featureModuleManager : FeatureModuleManager by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        featureModuleManager.loadModule(diModules())
    }

    override fun onDestroy() {
        super.onDestroy()
        featureModuleManager.unLoadModule(diModules())
    }

}
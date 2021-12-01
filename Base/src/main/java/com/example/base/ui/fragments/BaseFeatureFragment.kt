package com.example.base.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.multimoduleapp.di.ModuleManager
import com.example.multimoduleapp.navigation.Navigation
import com.example.multimoduleapp.navigation.NavigationImpl
import org.koin.android.ext.android.inject
import org.koin.core.module.Module

abstract class BaseFeatureFragment<VM : ViewModel> : Fragment() {
    private val loadScopedModules by inject<ModuleManager>()

    protected lateinit var navigation: Navigation

    protected abstract fun diModule() : Module

    protected abstract val vm : VM

    protected abstract val layout : Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigation = NavigationImpl(requireView().findNavController())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadScopedModules.addModule(diModule())
    }

    override fun onDestroy() {
        super.onDestroy()
        loadScopedModules.removeModule(diModule())
    }
}
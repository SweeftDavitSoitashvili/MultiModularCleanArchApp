package com.example.dashboard.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.base.ui.fragments.BaseFeatureFragment
import com.example.dashboard.R
import com.example.dashboard.ui.vm.DashboardVm
import com.example.dashboard.ui.vm.adapters.ResourceAdapter
import com.example.dashboarddomain.interactors.ResourceUseCase
import com.example.dashboarddomain.models.Resource
import com.example.multimoduleapp.di.ModuleManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.inject

class DashboardFragment : BaseFeatureFragment<DashboardVm>() {

    override val vm: DashboardVm by inject()

    override val layout: Int = R.layout.fragment_dashboard

    override fun diModule() = module {
        single(qualifier = named<DashboardFragment>()) { ResourceUseCase(get()) }
        viewModel { DashboardVm(get(qualifier = named<DashboardFragment>())) }
    }
    private lateinit var resourceAdapter : ResourceAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        makeResponseApiCall()
    }

    private fun initResourceAdapter(resources : List<Resource>) {
        val resourceRecyclerView = requireView().findViewById<RecyclerView>(R.id.resourceRecyclerView)
        resourceRecyclerView.layoutManager = LinearLayoutManager(requireView().context)
        resourceAdapter = ResourceAdapter(resources)
        resourceRecyclerView.adapter = resourceAdapter
    }

    private fun makeResponseApiCall()  {
        CoroutineScope(Dispatchers.Main).launch {
            initResourceAdapter(vm.getResource())
        }
    }
}


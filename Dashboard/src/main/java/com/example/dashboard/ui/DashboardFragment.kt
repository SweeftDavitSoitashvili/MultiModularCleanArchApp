package com.example.dashboard.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
import org.koin.core.qualifier.named
import org.koin.dsl.module

class DashboardFragment : Fragment() {

    private val loadScopedModules by inject<ModuleManager>()

    private lateinit var resourceAdapter : ResourceAdapter

    private val dashboardModule = module {
        single(qualifier = named<DashboardFragment>()) { ResourceUseCase(get()) }
        viewModel { DashboardVm(get(qualifier = named<DashboardFragment>())) }
    }

    private val dashboardVm : DashboardVm by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadScopedModules.addModule(dashboardModule)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

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
            initResourceAdapter(dashboardVm.getResource())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        loadScopedModules.removeModule(dashboardModule)
    }
}


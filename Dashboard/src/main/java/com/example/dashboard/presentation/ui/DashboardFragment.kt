package com.example.dashboard.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.base.ui.fragments.BaseFeatureFragment
import com.example.dashboard.R
import com.example.dashboard.domain.interactors.ResourceUseCase
import com.example.dashboard.presentation.ui.vm.DashboardVm
import com.example.dashboard.presentation.ui.vm.adapters.ResourceAdapter
import com.example.dashboard.domain.models.Resource
import com.example.navigation.Navigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

class DashboardFragment : BaseFeatureFragment<DashboardVm>() {

    override val vm: DashboardVm by inject()

    override val navigator: Navigator by inject()

    override val layout: Int = R.layout.fragment_dashboard

    override fun diModules() = module {
        factory(qualifier = named<DashboardFragment>()) { ResourceUseCase(get()) }
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


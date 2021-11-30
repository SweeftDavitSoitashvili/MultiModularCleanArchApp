package com.example.dashboard.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dashboard.R
import com.example.dashboard.ui.vm.DashboardVm
import com.example.dashboard.ui.vm.adapters.ResourceAdapter
import com.example.dashboarddomain.models.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {
    private val dashboardVm by lazy {
        ViewModelProvider(this)[DashboardVm::class.java]
    }

    private lateinit var resourceAdapter : ResourceAdapter

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
}


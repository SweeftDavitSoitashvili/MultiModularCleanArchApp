package com.example.dashboard.presentation.ui.vm.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dashboard.R
import com.example.dashboard.domain.models.Resource

class ResourceAdapter(private val resources: List<Resource>) :
    RecyclerView.Adapter<ResourceAdapter.ResourceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ResourceViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.resource_recycler_item, parent, false)
        )

    override fun onBindViewHolder(holder: ResourceViewHolder, position: Int) {
        holder.onBindResource(resources[position])
    }

    override fun getItemCount() = resources.size

    class ResourceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBindResource(resource: Resource) {
            with(itemView) {
                val resourceNameView = findViewById<TextView>(R.id.resourceNameView)
                val resourceYearView = findViewById<TextView>(R.id.resourceYearView)
                val resourcePantoneValueView = findViewById<TextView>(R.id.resourcePantoneValueView)
                val resourceItemView = findViewById<LinearLayout>(R.id.resourceItemView)

                resourceItemView.setBackgroundColor(Color.parseColor(resource.color))
                resourceNameView.text = resource.name
                resourceYearView.text = resource.year.toString()
                resourcePantoneValueView.text = resource.pantoneValue
            }
        }
    }

}
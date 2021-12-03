package com.example.base.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.navigation.Navigator

abstract class BaseFeatureFragment<VM : ViewModel> : Fragment() {

    protected abstract val vm : VM

    protected abstract val layout : Int

    protected abstract val navigator : Navigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container, false)
    }

}
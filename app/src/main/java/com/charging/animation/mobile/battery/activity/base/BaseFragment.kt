package com.charging.animation.mobile.battery.activity.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.charging.animation.mobile.battery.util.SystemUtil

abstract class BaseFragment<T : ViewBinding, V : ViewModel> : Fragment() {
    protected lateinit var binding: T
    protected lateinit var viewModel: V

    abstract fun getViewBinding(): T
    abstract fun setViewModel(): V

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        SystemUtil.setLocale(requireContext())
        binding = getViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = setViewModel()
        init()
        listener()
        viewModel()
    }

    abstract fun init()
    abstract fun listener()
    abstract fun viewModel()

}
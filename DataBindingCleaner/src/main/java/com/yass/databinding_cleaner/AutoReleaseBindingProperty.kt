package com.yass.databinding_cleaner

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class AutoReleaseBindingProperty<T : ViewDataBinding>(
    fragment: Fragment
) : ReadOnlyProperty<Fragment, T> {

    private var value: T? = null

    private val observer = object : DefaultLifecycleObserver {
        override fun onDestroy(owner: LifecycleOwner) {
            super.onDestroy(owner)
            value = null
            fragment.viewLifecycleOwner.lifecycle.removeObserver(this)
        }
    }

    init {
        fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
            viewLifecycleOwner.lifecycle.addObserver(observer)
        }
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val binding = this.value
        if (binding != null) return binding
        return DataBindingUtil.bind<T>(thisRef.requireView())!!.also {
            it.lifecycleOwner = thisRef.viewLifecycleOwner
            this.value = it
        }
    }
}

fun <T : ViewDataBinding> Fragment.autoReleaseBinding() = AutoReleaseBindingProperty<T>(this)
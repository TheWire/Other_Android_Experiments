package com.thewire.other_android_experiments.ui.viewmodels

import com.zhuinden.simplestack.ScopedServices

class XMLViewModel(val someValue: Int): ScopedServices.Registered {

        val someText = "some text"

    override fun onServiceRegistered() {
        println("service registered XMLViewModel")
    }

    override fun onServiceUnregistered() {
        println("service unregistered XMLViewModel")
    }
}
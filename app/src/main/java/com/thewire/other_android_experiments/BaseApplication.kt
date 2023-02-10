package com.thewire.other_android_experiments

import android.app.Application
import com.zhuinden.simplestack.GlobalServices
import com.zhuinden.simplestackextensions.servicesktx.add

class BaseApplication: Application() {
    lateinit var globalServices: GlobalServices
        private set

    override fun onCreate() {
        super.onCreate()
        globalServices = GlobalServices.builder()
            .add(42,"XML_INT")
            .build()
    }
}
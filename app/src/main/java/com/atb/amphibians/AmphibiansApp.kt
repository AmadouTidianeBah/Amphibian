package com.atb.amphibians

import android.app.Application
import com.atb.amphibians.data.ApplicationContainer
import com.atb.amphibians.data.DefaultApplicationContainer

class AmphibiansApp: Application() {
    lateinit var container: ApplicationContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultApplicationContainer()
    }
}
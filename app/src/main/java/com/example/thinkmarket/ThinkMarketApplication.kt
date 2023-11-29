package com.example.thinkmarket

import android.app.Application
import com.example.thinkmarket.data.AppContainer
import com.example.thinkmarket.data.DefaultAppContainer

class ThinkMarketApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
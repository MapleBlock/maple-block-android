package com.choius323.mapleblock

import android.app.Application
import com.choius323.mapleblock.di.dataModule
import com.choius323.mapleblock.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class MapleBlockApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        GlobalContext.startKoin {
            androidContext(this@MapleBlockApplication)
            modules(uiModule, dataModule)
        }
    }
}
package com.choius323.mapleblock

import android.app.Application
import com.choius323.mapleblock.di.dataModule
import com.choius323.mapleblock.di.uiModule
import com.choius323.mapleblock.di.useCaseModule
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class MapleBlockApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)
        GlobalContext.startKoin {
            androidContext(this@MapleBlockApplication)
            modules(uiModule, dataModule, useCaseModule)
        }
    }
}
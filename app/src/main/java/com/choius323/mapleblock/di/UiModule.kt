package com.choius323.mapleblock.di

import com.choius323.mapleblock.ui.screen.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::HomeViewModel)
//    viewModelOf(::DetailViewModel)
}
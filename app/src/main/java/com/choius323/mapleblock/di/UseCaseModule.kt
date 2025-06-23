package com.choius323.mapleblock.di

import com.choius323.mapleblock.usecase.GetNoticeListUseCase
import com.choius323.mapleblock.usecase.WritePostUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {
    singleOf(::WritePostUseCase)
    singleOf(::GetNoticeListUseCase)
}
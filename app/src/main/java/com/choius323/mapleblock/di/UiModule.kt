package com.choius323.mapleblock.di

import com.choius323.mapleblock.ui.screen.community.CommunityListViewModel
import com.choius323.mapleblock.ui.screen.communityarticle.CommunityArticleViewModel
import com.choius323.mapleblock.ui.screen.home.HomeViewModel
import com.choius323.mapleblock.ui.screen.imagepage.ImagePageViewModel
import com.choius323.mapleblock.ui.screen.notice.NoticeListViewModel
import com.choius323.mapleblock.ui.screen.onboarding.OnboardingViewModel
import com.choius323.mapleblock.ui.screen.writepost.WritePostViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::WritePostViewModel)
    viewModelOf(::NoticeListViewModel)
    viewModelOf(::OnboardingViewModel)
    viewModelOf(::CommunityListViewModel)
    viewModelOf(::CommunityArticleViewModel)
    viewModelOf(::ImagePageViewModel)
}
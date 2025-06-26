package com.choius323.mapleblock.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.choius323.mapleblock.R
import kotlinx.serialization.Serializable

sealed interface NavItem {
    val name: String?
        get() = this::class.simpleName

    val fullName: String?
        get() = this::class.qualifiedName

    sealed interface BottomNavItem : NavItem {

        @get:StringRes
        val nameRes: Int

        @get:DrawableRes
        val iconRes: Int

        @Serializable
        data object Home : BottomNavItem {
            override val iconRes: Int = R.drawable.ic_home
            override val nameRes: Int = R.string.bottom_nav_home
        }

        @Serializable
        data object NoticeList : BottomNavItem {
            override val iconRes: Int = R.drawable.ic_notice
            override val nameRes: Int = R.string.bottom_nav_notice
        }

        @Serializable
        data object Community : BottomNavItem {
            override val iconRes: Int = R.drawable.ic_community
            override val nameRes: Int = R.string.bottom_nav_community
        }

        @Serializable
        data object WhitePaper : BottomNavItem {
            override val iconRes: Int = R.drawable.ic_white_paper
            override val nameRes: Int = R.string.bottom_nav_white_paper
        }

        @Serializable
        data object MyHome : BottomNavItem {
            override val iconRes: Int = R.drawable.ic_setting
            override val nameRes: Int = R.string.bottom_nav_setting
        }

        companion object {
            val list: List<BottomNavItem>
                get() = listOf(Home, NoticeList, Community, WhitePaper, MyHome)
        }
    }

    @Serializable
    data class NoticeArticle(val id: Int) : NavItem

    @Serializable
    data object Profile : NavItem

    @Serializable
    data object WritePost : NavItem

    @Serializable
    data object Onboarding : NavItem
}
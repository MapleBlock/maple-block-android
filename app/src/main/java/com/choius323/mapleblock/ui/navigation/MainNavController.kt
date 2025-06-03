package com.choius323.mapleblock.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.choius323.mapleblock.ui.screen.notice.NoticeArticleScreen

@Stable
class MainNavController(controller: NavHostController) : MBNavController(controller)

@Composable
fun MainNavController(
    modifier: Modifier = Modifier,
    mainNavController: MainNavController = rememberMBNavController(),
) {
    val navController = mainNavController.navController

    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = MainNavItem.BottomNav,
    ) {
        composable<MainNavItem.BottomNav> { backStackEntry ->
            BottomNavController(Modifier.fillMaxSize(), goToNoticeArticle = { id ->
                navController.navigate(MainNavItem.NoticeArticle(id))
            })
        }
        composable<MainNavItem.NoticeArticle> { backStackEntry ->
            val notice = backStackEntry.savedStateHandle.toRoute<MainNavItem.NoticeArticle>()
            println("notice: $notice")
            NoticeArticleScreen(Modifier.fillMaxSize())
        }
    }
}

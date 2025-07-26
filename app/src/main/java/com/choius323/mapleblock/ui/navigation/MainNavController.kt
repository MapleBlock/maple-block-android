package com.choius323.mapleblock.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.choius323.mapleblock.ui.screen.community.CommunityScreen
import com.choius323.mapleblock.ui.screen.communityarticle.CommunityArticleScreen
import com.choius323.mapleblock.ui.screen.home.HomeScreen
import com.choius323.mapleblock.ui.screen.myhome.MyHomeScreen
import com.choius323.mapleblock.ui.screen.myhome.ProfileScreen
import com.choius323.mapleblock.ui.screen.notice.NoticeListScreen
import com.choius323.mapleblock.ui.screen.noticearticle.NoticeArticleScreen
import com.choius323.mapleblock.ui.screen.onboarding.OnboardingScreen
import com.choius323.mapleblock.ui.screen.whitepaper.WhitePaperScreen
import com.choius323.mapleblock.ui.screen.writepost.WritePostScreen
import kotlinx.coroutines.launch

@Stable
class MainNavController(controller: NavHostController) : MBNavController(controller)

@Composable
fun MainNavController(
    modifier: Modifier = Modifier,
    navController: MainNavController = rememberMBNavController(),
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
) {
    val coroutineScope = rememberCoroutineScope()

    MainBackHandler(navController)

    NavHost(
        navController = navController.navController,
        startDestination = NavItem.Onboarding,
        modifier = modifier,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        composable<NavItem.BottomNavItem.Home> { backStackEntry ->
            HomeScreen(
                showSnackBar = {
                    coroutineScope.launch {
                        snackBarHostState.showSnackbar(it)
                    }
                },
                goToNoticeArticle = {
                    navController.navigate(NavItem.NoticeArticle(it))
                }
            )
        }
        composable<NavItem.BottomNavItem.NoticeList> { backStackEntry ->
            NoticeListScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                goNoticeArticleScreen = {
                    navController.navigate(NavItem.NoticeArticle(it.toInt()))
                }
            )
        }
        composable<NavItem.BottomNavItem.Community> { backStackEntry ->
            CommunityScreen(
                Modifier.fillMaxSize(),
                goCommunityArticle = {
                    navController.navigate(NavItem.CommunityArticle(it))
                }
            ) {
                navController.navigate(NavItem.WritePost)
            }
        }
        composable<NavItem.BottomNavItem.WhitePaper> { backStackEntry ->
            WhitePaperScreen()
        }
        composable<NavItem.BottomNavItem.MyHome> { backStackEntry ->
            MyHomeScreen(goProfileScreen = {
                navController.navigate(NavItem.Profile)
            })
        }
        composable<NavItem.WritePost> {
            WritePostScreen(
                Modifier.fillMaxSize(),
                goBack = { navController.upPress() },
                onSuccessPost = { navController.upPress() },
            )
        }
        composable<NavItem.Profile> {
            ProfileScreen()
        }
        composable<NavItem.NoticeArticle> { backStackEntry ->
            val notice = backStackEntry.savedStateHandle.toRoute<NavItem.NoticeArticle>()
            NoticeArticleScreen(notice.id, Modifier.fillMaxSize())
        }
        composable<NavItem.Onboarding> { backStackEntry ->
            OnboardingScreen(
                modifier = Modifier.fillMaxSize(),
                goLogin = {
                    navController.navigate(NavItem.BottomNavItem.Home) {
                        launchSingleTop = true
                        popUpTo(NavItem.Onboarding) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable<NavItem.CommunityArticle> { backStackEntry ->
            CommunityArticleScreen(
                modifier = Modifier.fillMaxSize(),
                goBack = { navController.upPress() }
            )
        }
    }
}

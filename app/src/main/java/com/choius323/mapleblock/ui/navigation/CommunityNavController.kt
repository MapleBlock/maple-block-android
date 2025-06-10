package com.choius323.mapleblock.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.choius323.mapleblock.ui.screen.community.CommunityScreen
import com.choius323.mapleblock.ui.screen.writepost.WritePostScreen

@Stable
class CommunityNavController(controller: NavHostController) : MBNavController(controller)

@Composable
fun CommunityNavController(
    modifier: Modifier = Modifier,
    communityNavController: CommunityNavController = rememberMBNavController(),
) {
    NavHost(
        navController = communityNavController.navController,
        startDestination = CommunityNavItem.CommunityMain,
    ) {
        composable<CommunityNavItem.CommunityMain> {
            CommunityScreen(modifier.fillMaxSize()) {
                communityNavController.navigate(CommunityNavItem.WritePost)
            }
        }
        composable<CommunityNavItem.WritePost> {
            WritePostScreen(modifier.fillMaxSize()) {
                communityNavController.upPress()
            }
        }
    }
}
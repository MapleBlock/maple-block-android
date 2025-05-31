package com.choius323.mapleblock.ui.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.choius323.mapleblock.ui.screen.setting.ProfileScreen
import com.choius323.mapleblock.ui.screen.setting.SettingScreen


@Stable
class SettingNavController(controller: NavHostController) : MBNavController(controller)

@Composable
fun SettingNavController(
    modifier: Modifier = Modifier,
    settingNavController: SettingNavController = rememberMBNavController<SettingNavController>(),
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
) {
    val navController = settingNavController.navController
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = SettingNavItem.SettingMain
    ) {
        composable<SettingNavItem.SettingMain> {
            SettingScreen(goProfileScreen = {
                navController.navigate(SettingNavItem.Profile)
            })
        }
        composable<SettingNavItem.Profile> {
            ProfileScreen()
        }
    }
}
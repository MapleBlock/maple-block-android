package com.choius323.mapleblock.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController

@Composable
inline fun <reified T : MBNavController> rememberMBNavController(
    navController: NavHostController = rememberNavController()
): T = remember(navController) {
    when (T::class) {
        BottomNavController::class -> BottomNavController(navController) as T
        MainNavController::class -> MainNavController(navController) as T
        SettingNavController::class -> SettingNavController(navController) as T
        else -> throw IllegalArgumentException("Unsupported MBNavController type: ${T::class}")
    }
}

@Stable
abstract class MBNavController(val navController: NavHostController) {
    fun upPress() {
        navController.navigateUp()
    }

    fun <T : Any> navigateToBottomBarRoute(route: T) {
        if (route != navController.currentDestination?.route) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true
                /*
                 Pop up backstack to the first destination and save state. This makes going back
                 to the start destination when pressing back in any other bottom tab.
                 */
                popUpTo(findStartDestination(navController.graph).id) {
                    saveState = true
                }
            }
        }
    }

    fun navigate(from: NavBackStackEntry? = null, to: NavHostController.() -> Unit) {
        if (from == null || from.lifecycleIsResumed()) {
            navController.to()
        }
    }

    fun <T : Any> navigate(
        to: T, from: NavBackStackEntry? = null, navOptions: NavOptions? = null
    ) {
        if (from == null || from.lifecycleIsResumed()) {
            navController.navigate(to, navOptions)
        }
    }

//    fun navigateToSnackDetail(snackId: Long, origin: String, from: NavBackStackEntry) {
//        // In order to discard duplicated navigation events, we check the Lifecycle
//        if (from.lifecycleIsResumed()) {
//            navController.navigate("${MainDestinations.SNACK_DETAIL_ROUTE}/$snackId?origin=$origin")
//        }
//    }
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
fun NavBackStackEntry.lifecycleIsResumed() = this.lifecycle.currentState == Lifecycle.State.RESUMED

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

/**
 * Copied from similar function in NavigationUI.kt
 *
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:navigation/navigation-ui/src/main/java/androidx/navigation/ui/NavigationUI.kt
 */
private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}

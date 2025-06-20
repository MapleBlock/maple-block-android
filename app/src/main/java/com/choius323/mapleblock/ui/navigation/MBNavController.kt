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
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions

@Composable
inline fun <reified T : MBNavController> rememberMBNavController(
    navController: NavHostController = rememberNavController(),
): T = remember(navController) {
    T::class.constructors.first().call(navController)
}

@Stable
abstract class MBNavController(val navController: NavHostController) {
    fun upPress() {
        navController.navigateUp()
    }

    fun <T : NavItem> navigate(
        to: T, from: NavBackStackEntry? = null, navBuilder: NavOptionsBuilder.() -> Unit = {},
    ) {
        navigate(
            to = to,
            from = from ?: navController.currentBackStackEntry,
            navOptions = navOptions(navBuilder)
        )
    }

    fun <T : NavItem> navigate(
        to: T, from: NavBackStackEntry?, navOptions: NavOptions? = null,
    ) {
        if (from == null || from.lifecycleIsResumed()) {
            navController.navigate(to, navOptions)
        }
    }

    fun <T : NavItem> navigateNow(
        to: T, navBuilder: NavOptionsBuilder.() -> Unit = {},
    ) {
        navController.navigate(
            to,
            navOptions = navOptions(navBuilder)
        )
    }

    fun navigateToHome() {
        navigateToBottomBarRoute(NavItem.BottomNavItem.Home)
    }

    fun navigateToBottomBarRoute(route: NavItem) {
        if (route.fullName != navController.currentDestination?.route) {
            // 다른 탭일 때
            navigate(
                to = route, from = navController.currentBackStackEntry, navOptions = navOptions {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(findStartDestination(navController.graph).id) {
                        saveState = true
                    }
                })
        } else {
            val currentRoute = navController.currentBackStackEntry?.destination?.route
            val isAtRootScreen = currentRoute != null && NavItem.BottomNavItem.list.any { item ->
                currentRoute == item.fullName
            }
            if (isAtRootScreen) {
                // 같은 탭의 최상위 화면일 때
                navigate(route) {
                    popUpTo(route) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            } else {
                upPress()
            }
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
tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}

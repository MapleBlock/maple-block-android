package com.choius323.mapleblock.ui.navigation

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.svg.SvgDecoder
import com.choius323.mapleblock.ui.component.MBSnackBar
import com.choius323.mapleblock.ui.component.MBText
import com.choius323.mapleblock.ui.screen.home.HomeScreen
import com.choius323.mapleblock.ui.screen.notice.NoticeScreen
import com.choius323.mapleblock.ui.screen.whitepaper.WhitePaperScreen
import com.choius323.mapleblock.ui.theme.Gray30
import kotlinx.coroutines.launch

@Stable
class BottomNavController(controller: NavHostController) : MBNavController(controller) {

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
                navigate(route, navOptions = navOptions {
                    popUpTo(route) {
                        inclusive = true
                    }
                    launchSingleTop = true
                })
            } else {
                upPress()
            }
        }
    }
}

@Composable
fun BottomNavController(
    modifier: Modifier = Modifier,
    bottomNavController: BottomNavController = rememberMBNavController(),
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    goToNoticeArticle: (Int) -> Unit = {},
) {
    val navController = bottomNavController.navController
    val coroutineScope = rememberCoroutineScope()

    BottomBackHandler(bottomNavController)

    Scaffold(modifier = modifier.background(MaterialTheme.colorScheme.primary), bottomBar = {
        MBBottomBar(bottomNavController)
    }, snackbarHost = {
        SnackbarHost(snackBarHostState) { snackData ->
            MBSnackBar(snackData.visuals.message)
        }
    }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavItem.BottomNavItem.Home,
            modifier = modifier.padding(innerPadding)
        ) {
            composable<NavItem.BottomNavItem.Home> { backStackEntry ->
                HomeScreen(
                    showSnackBar = {
                        coroutineScope.launch {
                            snackBarHostState.showSnackbar(it)
                        }
                    },
                    goToNoticeArticle = goToNoticeArticle
                )
            }
            composable<NavItem.BottomNavItem.Notice> { backStackEntry ->
                NoticeScreen()
            }
            composable<NavItem.BottomNavItem.Community> { backStackEntry ->
                CommunityNavController()
            }
            composable<NavItem.BottomNavItem.WhitePaper> { backStackEntry ->
                WhitePaperScreen()
            }
            composable<NavItem.BottomNavItem.Setting> { backStackEntry ->
                SettingNavController()
            }
        }
    }
}

@Composable
fun BottomBackHandler(bottomNavController: BottomNavController) {
    val backStackEntry by bottomNavController.navController.currentBackStackEntryAsState()
    val context = LocalContext.current

    var backPressedTime by remember { mutableLongStateOf(0L) }

    // 현재 화면이 루트 화면인지 확인
    val isAtRootScreen by remember {
        derivedStateOf {
            val currentRoute = backStackEntry?.destination?.route
            currentRoute != null && NavItem.BottomNavItem.list.any { item ->
                currentRoute == item.fullName
            }
        }
    }

    // 현재 화면이 Home인지 확인
    val isAtHome by remember {
        derivedStateOf {
            backStackEntry?.destination?.hasRoute(NavItem.BottomNavItem.Home::class) == true
        }
    }

    // 뒤로가기 처리
    BackHandler {
        if (isAtRootScreen) {
            if (isAtHome) {
                // Home 화면에서 뒤로가기
                val currentTime = System.currentTimeMillis()
                if (currentTime - backPressedTime < 2000) {
                    // 2초 안에 다시 누르면 앱 종료
                    (context as? Activity)?.finish()
                } else {
                    // 첫 번째 뒤로가기 - 토스트 표시
                    backPressedTime = currentTime
                    Toast.makeText(context, "뒤로 버튼을 한 번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
                }
            } else {
                // 다른 루트 화면에서 뒤로가기
                bottomNavController.navigateToHome()
            }
        } else {
            // 루트가 아닌 화면에서는 일반 뒤로가기
            bottomNavController.upPress()
        }
    }
}

@Composable
fun MBBottomBar(
    bottomNavController: BottomNavController,
    modifier: Modifier = Modifier,
) {
    val backStackEntry by bottomNavController.navController.currentBackStackEntryAsState()
    Row(
        modifier
            .fillMaxWidth()
            .height(74.dp)
            .padding(vertical = 12.dp, horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        NavItem.BottomNavItem.list.forEach { item ->
            val isSelected = remember(backStackEntry?.destination) {
                backStackEntry?.destination?.hierarchy?.any { it.hasRoute(item::class) } == true
            }
            val color by derivedStateOf {
                if (isSelected) Color.Black else Gray30
            }
            Column(
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable {
                        bottomNavController.navigateToBottomBarRoute(item)
                    }, horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current).data(item.iconRes)
                        .decoderFactory(SvgDecoder.Factory()).build(),
                    contentDescription = item.name,
                    modifier = Modifier.size(24.dp),
                    colorFilter = ColorFilter.tint(color),
                    clipToBounds = true,
                )
                Spacer(modifier = Modifier.height(8.dp))
                MBText(stringResource(item.nameRes), fontSize = 12.sp, color = color)
            }
        }
    }
}

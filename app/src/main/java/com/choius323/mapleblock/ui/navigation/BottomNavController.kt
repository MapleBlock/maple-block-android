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
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.svg.SvgDecoder
import com.choius323.mapleblock.ui.component.MBSnackBar
import com.choius323.mapleblock.ui.component.MBText
import com.choius323.mapleblock.ui.screen.community.CommunityScreen
import com.choius323.mapleblock.ui.screen.home.HomeScreen
import com.choius323.mapleblock.ui.screen.notice.NoticeScreen
import com.choius323.mapleblock.ui.screen.setting.SettingScreen
import com.choius323.mapleblock.ui.screen.whitepaper.WhitePaperScreen
import com.choius323.mapleblock.ui.theme.Gray30
import kotlinx.coroutines.launch

@Stable
class BottomNavController(controller: NavHostController) : MBNavController(controller) {

    fun <T : BottomNavItem> navigate(
        to: T,
        from: NavBackStackEntry? = null,
        navOptions: NavOptions? = null
    ) {
        super.navigate(to, from = from, navOptions = navOptions)
    }

    fun navigateToBottomTab(item: BottomNavItem) {
        val currentDestination = navController.currentDestination
        val isCurrentTab = currentDestination?.hierarchy?.any { it.hasRoute(item::class) } == true

        if (isCurrentTab) {
            // 같은 탭을 클릭한 경우 - 루트로 이동하여 새로고침
            navController.navigate(item) {
                popUpTo(item) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        } else {
            // 다른 탭으로 이동하는 경우 - 상태 보존
            navigateToBottomBarRoute(item)
        }
    }

    fun navigateToHome() {
        navigateToBottomBarRoute(BottomNavItem.Home)
    }
}

@Composable
fun BottomNavController(
    modifier: Modifier = Modifier,
    bottomNavController: BottomNavController = rememberMBNavController(),
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    goToNoticeArticle: (Int) -> Unit = {},
) {
    val context = LocalContext.current
    val navController = bottomNavController.navController
    val backStackEntry by navController.currentBackStackEntryAsState()

    // 뒤로가기 처리를 위한 상태
    var backPressedTime by remember { mutableLongStateOf(0L) }

    // 현재 화면이 루트 화면인지 확인
    val isAtRootScreen = remember(backStackEntry?.destination) {
        backStackEntry?.destination?.hierarchy?.any { destination ->
            BottomNavItem.list.any { it::class.qualifiedName == destination.route }
        } == true
    }

    // 현재 화면이 Home인지 확인
    val isAtHome = remember(backStackEntry?.destination) {
        backStackEntry?.destination?.hasRoute(BottomNavItem.Home::class) == true
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
                // 다른 루트 화면에서 뒤로가기 - Home으로 이동
                bottomNavController.navigateToHome()
            }
        } else {
            // 루트가 아닌 화면에서는 일반 뒤로가기
            navController.navigateUp()
        }
    }

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier.background(MaterialTheme.colorScheme.primary),
        bottomBar = {
            MBBottomBar(bottomNavController)
        },
        snackbarHost = {
            SnackbarHost(snackBarHostState) { snackData ->
                MBSnackBar(snackData.visuals.message)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home,
            modifier = modifier.padding(innerPadding)
        ) {
            composable<BottomNavItem.Home> { backStackEntry ->
                HomeScreen(
                    showSnackBar = {
                        coroutineScope.launch {
                            snackBarHostState.showSnackbar(it)
                        }
                    },
                    goToNoticeArticle = goToNoticeArticle
                )
            }
            composable<BottomNavItem.Notice> { backStackEntry ->
                NoticeScreen()
            }
            composable<BottomNavItem.Community> { backStackEntry ->
                CommunityScreen()
            }
            composable<BottomNavItem.WhitePaper> { backStackEntry ->
                WhitePaperScreen()
            }
            composable<BottomNavItem.Setting> { backStackEntry ->
                SettingNavController()
            }
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
        BottomNavItem.list.forEach { item ->
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
                        bottomNavController.navigateToBottomTab(item)
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.iconRes)
                        .decoderFactory(SvgDecoder.Factory())
                        .build(),
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

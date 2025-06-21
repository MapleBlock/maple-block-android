package com.choius323.mapleblock.ui.navigation

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MainBackHandler(navController: MainNavController) {
    val backStackEntry by navController.navController.currentBackStackEntryAsState()
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
                navController.navigateToHome()
            }
        } else {
            // 루트가 아닌 화면에서는 일반 뒤로가기
            navController.upPress()
        }
    }
}
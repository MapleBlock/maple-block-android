package com.choius323.mapleblock.ui.navigation

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.svg.SvgDecoder
import com.choius323.mapleblock.ui.component.MBHorizonDivider
import com.choius323.mapleblock.ui.component.MBText
import com.choius323.mapleblock.ui.theme.MBColor
import com.choius323.mapleblock.ui.theme.MBTypo

@Composable
fun BottomNavigationBar(
    navController: MainNavController,
    modifier: Modifier = Modifier,
) {
    val backStackEntry by navController.navController.currentBackStackEntryAsState()
    // 현재 route가 bottomBarScreens에 포함되는지 확인
    val currentRoute = backStackEntry?.destination?.route
    val shouldShowBottomBar = NavItem.BottomNavItem.list.any { screen ->
        currentRoute == screen.fullName
    }
    val primaryColor = MaterialTheme.colorScheme.primary

    if (shouldShowBottomBar.not()) return
    Column(
        modifier
            .fillMaxWidth()
            .height(104.dp)
            .padding(vertical = 12.dp),
    ) {
        MBHorizonDivider()
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            NavItem.BottomNavItem.list.forEach { item ->
                val isSelected = remember(backStackEntry?.destination) {
                    backStackEntry?.destination?.hierarchy?.any { it.hasRoute(item::class) } == true
                }
                val itemColor by derivedStateOf {
                    if (isSelected) primaryColor else MBColor.Gray300
                }
                val backgroundColor by derivedStateOf {
                    if (isSelected) MBColor.Gray100 else MBColor.White
                }
                Column(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable { navController.navigateToBottomBarRoute(item) }
                        .background(backgroundColor),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current).data(item.iconRes)
                            .decoderFactory(SvgDecoder.Factory()).build(),
                        contentDescription = item.name,
                        modifier = Modifier.size(24.dp),
                        colorFilter = ColorFilter.tint(itemColor),
                        clipToBounds = true,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    MBText(stringResource(item.nameRes), style = MBTypo.Body2, color = itemColor)
                }
            }
        }
    }
}
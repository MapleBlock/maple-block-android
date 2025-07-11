package com.choius323.mapleblock.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.referentialEqualityPolicy
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.currentBackStackEntryAsState
import com.choius323.mapleblock.ui.icon.Back
import com.choius323.mapleblock.ui.icon.MBIcons
import com.choius323.mapleblock.ui.icon.Search
import com.choius323.mapleblock.ui.navigation.MainNavController
import com.choius323.mapleblock.ui.theme.MBTheme

@Composable
fun MBTopAppBar(
    mainNavController: MainNavController,
    modifier: Modifier = Modifier,
) {
    val navBackStackEntry by mainNavController.navController.currentBackStackEntryAsState()
    navBackStackEntry?.let { entry ->
        val viewModel: TopAppBarViewModel = viewModel(
            viewModelStoreOwner = entry,
            initializer = { TopAppBarViewModel() },
        )
        MBTopAppBar(
            viewModel.navigationIcon,
            viewModel.title,
            viewModel.actions,
            viewModel.showAppBar,
            modifier,
        )
    }
}

@Composable
private fun MBTopAppBar(
    navigationIcon: @Composable () -> Unit,
    title: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit,
    showAppBar: Boolean,
    modifier: Modifier = Modifier,
) {
    if (showAppBar.not()) return
    Box(
        modifier
            .padding(vertical = 18.dp, horizontal = 22.dp)
            .height(IntrinsicSize.Min)
            .background(Color.Transparent),
    ) {
        Box(Modifier.align(Alignment.Center)) {
            title()
        }
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            navigationIcon()
            Row(horizontalArrangement = Arrangement.End) {
                actions()
            }
        }
    }
}

@Preview(widthDp = 300, showBackground = true)
@Preview(widthDp = 150, showBackground = true)
@Composable
private fun MBTopAppBarPreview() {
    MBTheme {
        Surface {
            MBTopAppBar(
                navigationIcon = {
                    Icon(MBIcons.Pixel.Back, null)
                },
                title = {
                    Text("Title Preview", maxLines = 1)
                },
                actions = {
                    repeat(4) {
                        Icon(MBIcons.Pixel.Search, null)
                    }
                },
                showAppBar = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(widthDp = 300, showBackground = true)
@Composable
private fun MBTopAppBarPreview2() {
    MBTheme {
        Surface {
            MBTopAppBar(
                navigationIcon = {
                    Icon(MBIcons.Pixel.Back, null)
                },
                title = {
                    MBText("Title Preview 2", maxLines = 1)
                },
                actions = {},
                showAppBar = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

private class TopAppBarViewModel : ViewModel() {
    var showAppBar by mutableStateOf(false)
    var title by mutableStateOf<@Composable () -> Unit>({ }, referentialEqualityPolicy())
    var navigationIcon by mutableStateOf<@Composable () -> Unit>({ }, referentialEqualityPolicy())
    var actions by mutableStateOf<@Composable RowScope.() -> Unit>({ }, referentialEqualityPolicy())
}

@Composable
fun ProvideAppBar(
    showAppBar: Boolean = true,
    navigationIcon: @Composable () -> Unit = {},
    title: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {
    val viewModelStoreOwner = LocalViewModelStoreOwner.current
    (viewModelStoreOwner as? NavBackStackEntry)?.let { owner ->
        val viewModel: TopAppBarViewModel = viewModel(
            viewModelStoreOwner = owner,
            initializer = { TopAppBarViewModel() },
        )
        LaunchedEffect(showAppBar, title, navigationIcon, actions) {
            viewModel.showAppBar = showAppBar
            viewModel.title = title
            viewModel.navigationIcon = navigationIcon
            viewModel.actions = actions
        }
    }
}

package com.choius323.mapleblock.ui.screen.home

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.choius323.mapleblock.ui.component.MBText
import com.choius323.mapleblock.ui.component.ProvideAppBar
import com.choius323.mapleblock.ui.icon.Forward
import com.choius323.mapleblock.ui.icon.MBIcons
import com.choius323.mapleblock.ui.screen.home.HomeSideEffect.ShowToast
import com.choius323.mapleblock.ui.theme.MBTypo
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
    showSnackBar: (String) -> Unit = {},
    goToNoticeArticle: (Int) -> Unit = {},
) {
    val uiState by viewModel.collectAsState()
    val context = LocalContext.current

    ProvideAppBar(
        showAppBar = true,
        navigationIcon = { MBText("Maple Story U 백서", style = MBTypo.Title2) },
        actions = { Icon(MBIcons.Pixel.Forward, null) }
    )

    viewModel.collectSideEffect {
        when (it) {
            is ShowToast -> {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    HomeScreen(uiState, goToNoticeArticle = goToNoticeArticle)
}

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    modifier: Modifier = Modifier,
    goToNoticeArticle: (Int) -> Unit = {},
) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        uiState.notices.forEachIndexed { index, it ->
            Column(
                Modifier
                    .fillMaxWidth()
                    .clickable { goToNoticeArticle(index) }) {
                Text(it.title)
                Text(it.content)
            }
        }
        repeat(50) {
            Text("$it     ".repeat(10))
        }
    }
}

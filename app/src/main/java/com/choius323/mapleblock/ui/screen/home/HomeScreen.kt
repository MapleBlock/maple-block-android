package com.choius323.mapleblock.ui.screen.home

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.choius323.mapleblock.ui.screen.home.HomeSideEffect.ShowToast
import com.choius323.mapleblock.ui.screen.splash.SplashActivity
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

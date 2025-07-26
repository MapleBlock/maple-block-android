package com.choius323.mapleblock.ui.screen.imagepage

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.choius323.mapleblock.R
import com.choius323.mapleblock.ui.component.ProvideAppBar
import com.choius323.mapleblock.ui.icon.Back
import com.choius323.mapleblock.ui.icon.MBIcons
import com.choius323.mapleblock.ui.theme.MBTheme
import kotlinx.coroutines.launch
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun ImagePageScreen(
    modifier: Modifier = Modifier,
    viewModel: ImagePageViewModel = koinViewModel(),
    goBack: () -> Unit = {},
) {
    val uiState by viewModel.collectAsState()
    val context = LocalContext.current
    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is ImagePageSideEffect.GoBack -> goBack()
            is ImagePageSideEffect.ShowToast ->
                Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
        }
    }

    ProvideAppBar(
        showAppBar = true,
        navigationIcon = {
            Icon(
                MBIcons.Pixel.Back,
                "뒤로 가기",
                modifier = Modifier.clickable { viewModel.onEvent(ImagePageUiEvent.OnClickBackButton) }
            )
        }
    )

    ImagePageScreenContent(
        uiState, modifier, onEvent = viewModel::onEvent
    )
}

@Composable
fun ImagePageScreenContent(
    uiState: ImagePageUiState,
    modifier: Modifier = Modifier,
    onEvent: (ImagePageUiEvent) -> Unit = {},
) {
    val pagerState =
        rememberPagerState(pageCount = uiState.imageList::size, initialPage = uiState.initialPage)
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(uiState.initialPage) {
        pagerState.scrollToPage(uiState.initialPage)
    }
    Box(
        modifier = modifier,
    ) {
        HorizontalPager(
            pagerState,
            Modifier.fillMaxSize()
        ) { page ->
            println("HorizontalPager $page")
            AsyncImage(
                uiState.imageList[page],
                "이미지",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .zoomable(rememberZoomState()),
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp)
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for ((index, image) in uiState.imageList.withIndex()) {
                ImagePreviewSection(
                    image, index == pagerState.currentPage,
                    modifier = Modifier.clickable {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun ImagePreviewSection(
    image: Any,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
) {
    val selectedModifier = remember {
        Modifier
            .padding(horizontal = 8.dp)
            .size(40.dp)
    }
    val unselectedModifier = remember { Modifier.size(32.dp) }
    AsyncImage(
        image,
        "이미지 미리보기",
        modifier = modifier.then(if (isSelected) selectedModifier else unselectedModifier),
        contentScale = ContentScale.Crop,
        filterQuality = FilterQuality.Low,
    )
}

@Preview
@Composable
private fun ImagePageScreenContentPreview() {
    MBTheme {
        ImagePageScreenContent(
            uiState = ImagePageUiState(
                imageList = listOf(
                    R.drawable.ic_title,
                    R.drawable.ic_launcher_foreground,
                    R.drawable.img_checker
                )
            ),
            modifier = Modifier.fillMaxSize(),
        )
    }
}
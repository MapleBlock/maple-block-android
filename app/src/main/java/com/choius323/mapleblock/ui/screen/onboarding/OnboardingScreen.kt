package com.choius323.mapleblock.ui.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.choius323.mapleblock.R
import com.choius323.mapleblock.ui.component.MBText
import com.choius323.mapleblock.ui.model.OnboardingItem
import com.choius323.mapleblock.ui.theme.MBTheme
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = koinViewModel(),
    goLogin: () -> Unit,
) {
    val state = viewModel.collectAsState().value

    // Side Effect를 구독하여 네비게이션 처리
    LaunchedEffect(Unit) {
        viewModel.container.sideEffectFlow.collectLatest { sideEffect ->
            when (sideEffect) {
                is OnboardingSideEffect.GoLogin -> {
                    goLogin()
                }
            }
        }
    }

    OnboardingScreenContent(state, viewModel::onEvent, modifier)
}

@Composable
fun OnboardingScreenContent(
    state: OnboardingUiState,
    onEvent: (OnboardingUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(pageCount = { state.onboardingItems.size })

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        PagerIndicator(
            pageCount = state.onboardingItems.size, currentPage = pagerState.currentPage
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) { page ->
            OnboardingPageItem(item = state.onboardingItems[page])
        }

        // 하단 로그인 버튼
        Button(
            onClick = { onEvent(OnboardingUiEvent.OnClickLogin) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(1.dp, Color.Black, CutCornerShape(0.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            shape = CutCornerShape(0.dp)
        ) {
            MBText(
                text = "로그인 바로가기", fontSize = 16.sp, fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun OnboardingPageItem(item: OnboardingItem) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxHeight()
    ) {
        val imageResId = when (item.imageName) {
            OnboardingItem.ImageName.Notices -> R.drawable.ic_notice
            OnboardingItem.ImageName.Challenge -> R.drawable.ic_community
            OnboardingItem.ImageName.Friends -> R.drawable.ic_setting
        }
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = item.title,
            modifier = Modifier
                .aspectRatio(1f)
                .height(300.dp)
                .background(Color.LightGray, RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(40.dp))

        MBText(
            text = item.title, style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        MBText(
            text = item.description,
            style = MaterialTheme.typography.bodyLarge.copy(
                textAlign = TextAlign.Center
            ),
        )
    }
}

@Composable
fun PagerIndicator(pageCount: Int, currentPage: Int) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { iteration ->
            val color =
                if (currentPage == iteration) MaterialTheme.colorScheme.primary else Color.LightGray
            Box(
                modifier = Modifier
                    .size(if (currentPage == iteration) 10.dp else 8.dp)
                    .clip(RectangleShape)
                    .background(color)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    val items = listOf(
        OnboardingItem(
            imageName = OnboardingItem.ImageName.Notices,
            title = "다양한 채널의 공지사항",
            description = "한국어 번역 제공"
        ),
        OnboardingItem(
            imageName = OnboardingItem.ImageName.Challenge,
            title = "나만의 챌린지",
            description = "목표를 설정하고 달성해보세요"
        ),
        OnboardingItem(
            imageName = OnboardingItem.ImageName.Friends,
            title = "친구와 함께",
            description = "경쟁하고 성장하는 재미"
        )
    )
    MBTheme {
        OnboardingScreenContent(
            OnboardingUiState(
                onboardingItems = items,
            ),
            onEvent = {}
        )
    }
}
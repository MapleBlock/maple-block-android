package com.choius323.mapleblock.ui.screen.community

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.choius323.mapleblock.ui.component.MBChip
import com.choius323.mapleblock.ui.component.MBText
import com.choius323.mapleblock.ui.component.ProvideAppBar
import com.choius323.mapleblock.ui.icon.Add
import com.choius323.mapleblock.ui.icon.MBIcons
import com.choius323.mapleblock.ui.icon.Search
import com.choius323.mapleblock.ui.model.CommunityListItem
import com.choius323.mapleblock.ui.theme.MBTheme
import com.choius323.mapleblock.ui.theme.MBTypo
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun CommunityScreen(
    modifier: Modifier = Modifier,
    viewModel: CommunityListViewModel = koinViewModel(),
    goCommunityArticle: (articleId: Long) -> Unit,
    goWritePost: () -> Unit,
) {
    val state by viewModel.collectAsState()
    ProvideAppBar(
        showAppBar = true,
        navigationIcon = { MBText("커뮤니티", style = MBTypo.Title2) },
        actions = { Icon(MBIcons.Pixel.Search, contentDescription = "Search") }
    )
    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is CommunityListSideEffect.GoWriteCommunityArticle -> goWritePost()
            is CommunityListSideEffect.GoCommunityArticle -> goCommunityArticle(sideEffect.articleId)
        }
    }

    CommunityScreenContent(
        uiState = state,
        onEvent = viewModel::onEvent,
        modifier = modifier
    )
}

@Composable
fun CommunityScreenContent(
    uiState: CommunityListUiState,
    onEvent: (CommunityListUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Column(modifier) {
            CategoryChipRow(
                modifier = Modifier,
                categoryList = listOf("모두", "가이드", "팁", "자유"),
                selectedCategoryIndex = 0,
                onCategoryClick = {}
            )
            Spacer(Modifier.height(16.dp))
            LazyColumn(Modifier.fillMaxWidth()) {
                itemsIndexed(uiState.communityList) { index, communityListItem ->
                    CommunityListItem(
                        modifier = Modifier.clickable {
                            onEvent(CommunityListUiEvent.OnClickCommunityArticle(communityListItem.articleId))
                        },
                        data = communityListItem,
                    )
                    if (index < uiState.communityList.lastIndex) {
                        HorizontalDivider(
                            thickness = 2.dp,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )
                    }
                }
            }
        }
        Icon(
            MBIcons.Pixel.Add,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(y = (-24).dp)
                .size(72.dp)
                .background(MaterialTheme.colorScheme.background)
                .clickable { onEvent(CommunityListUiEvent.WriteCommunityArticle) }
                .border(2.dp, Color.Black)
                .padding(24.dp),
            contentDescription = "글 추가",
        )
    }
}

@Composable
fun CategoryChipRow(
    modifier: Modifier = Modifier,
    categoryList: List<String>,
    selectedCategoryIndex: Int,
    onCategoryClick: (index: Int) -> Unit,
) {
    LazyRow(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        itemsIndexed(items = categoryList) { index, category ->
            MBChip(
                text = category,
                isSelected = selectedCategoryIndex == index,
                onClick = { onCategoryClick(index) }
            )
        }
    }
}

@Preview
@Composable
private fun CommunityScreenContentPreview() {
    val sampleData = CommunityListItem.sample1
    MBTheme {
        Surface {
            CommunityScreenContent(
                uiState = CommunityListUiState(communityList = List(3) { sampleData }),
                onEvent = {},
                modifier = Modifier
            )
        }
    }
}
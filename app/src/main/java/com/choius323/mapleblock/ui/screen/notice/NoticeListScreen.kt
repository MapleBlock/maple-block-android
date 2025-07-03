package com.choius323.mapleblock.ui.screen.notice

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.choius323.mapleblock.ui.component.MBChip
import com.choius323.mapleblock.ui.component.MBText
import com.choius323.mapleblock.ui.component.ProvideAppBar
import com.choius323.mapleblock.ui.model.Notice
import com.choius323.mapleblock.ui.theme.MBColor
import com.choius323.mapleblock.ui.theme.MBTheme
import com.choius323.mapleblock.ui.theme.MBTypo
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun NoticeListScreen(
    modifier: Modifier = Modifier,
    viewModel: NoticeListViewModel = koinViewModel(),
    goNoticeArticleScreen: (Long) -> Unit,
) {
    val context = LocalContext.current
    val state by viewModel.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.onEvent(NoticeListUiEvent.LoadData("Discord"))
    }
    ProvideAppBar(showAppBar = true, navigationIcon = {
        MBText("공지사항", style = MBTypo.Title2)
    }, actions = {
        Icon(Icons.Default.Search, "검색")
    })
    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is NoticeListSideEffect.ShowToast -> {
                Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
            }

            is NoticeListSideEffect.GoNoticeArticleScreen -> goNoticeArticleScreen(sideEffect.articleId)
        }
    }
    NoticeListScreenContent(
        state = state, onEvent = { viewModel.onEvent(it) }, modifier = modifier
    )
}

@Composable
private fun NoticeListScreenContent(
    state: NoticeListUiState,
    onEvent: (NoticeListUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        LazyRow(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items(listOf("모두", "Discord", "X", "Telegram", "WebSide")) {
                MBChip(text = it, isSelected = it == "Discord", onClick = { /*TODO*/ })
            }
        }
        Spacer(Modifier.height(24.dp))
        LazyColumn(
            Modifier.fillMaxWidth(),
        ) {
            itemsIndexed(state.noticeList, key = { _, notice -> notice.id }) { index, item ->
                NoticeListItem(
                    notice = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onEvent(NoticeListUiEvent.ClickNotice(item.id)) })
                if (index != state.noticeList.lastIndex) {
                    HorizontalDivider(
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun NoticeListItem(
    notice: Notice,
    modifier: Modifier = Modifier,
) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        MBText(
            "Discord",
            Modifier
                .background(MBColor.Discord)
                .padding(horizontal = 8.dp, vertical = 4.dp),
            fontSize = 12.sp
        )
        MBText(notice.title, style = MBTypo.Subtitle2)
        MBText("2025. 05. 05", color = MBColor.Gray300, style = MBTypo.Body3)
    }
}

@Preview
@Composable
private fun NoticeListScreenContentPreview() {
    val list = listOf(
        Notice(1, "Notice 1", "Notice 1 Content"),
        Notice(2, "Notice 2 Notice 2 Notice 2 Notice 2", "Notice 2 Content"),
        Notice(
            3,
            "Notice 3 Notice 3 Notice 3 Notice 3 Notice 3 Notice 3 Notice 3 Notice 3 Notice 3 Notice 3 Notice 3 ",
            "Notice 3 Content"
        ),
        Notice(4, "Notice 4", "Notice 4 Content"),
        Notice(5, "Notice 5", "Notice 5 Content"),
        Notice(6, "Notice 6", "Notice 6 Content"),
    )
    MBTheme {
        Surface {
            NoticeListScreenContent(
                NoticeListUiState(noticeList = list),
                onEvent = {},
            )
        }
    }
}

@Preview
@Composable
private fun NoticeListItemPreview() {
    val notice = Notice(1, "Notice 1", "Notice 1 Content")
    MBTheme {
        Surface {
            NoticeListItem(
                notice = notice,
                modifier = Modifier
            )
        }
    }
}
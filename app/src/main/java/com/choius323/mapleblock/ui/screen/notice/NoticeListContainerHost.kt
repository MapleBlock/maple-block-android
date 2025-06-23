package com.choius323.mapleblock.ui.screen.notice

import com.choius323.mapleblock.ui.model.Notice

data class NoticeListUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val noticeList: List<Notice> = emptyList(),
    val isLoaded: Boolean = false,
)

sealed interface NoticeListUiEvent {
    data class LoadData(val type: String, val isForceLoad: Boolean = false) : NoticeListUiEvent
    data class ClickNotice(val articleId: Long) : NoticeListUiEvent
}

sealed interface NoticeListSideEffect {
    data class ShowToast(val message: String) : NoticeListSideEffect
    data class GoNoticeArticleScreen(val articleId: Long) : NoticeListSideEffect
}
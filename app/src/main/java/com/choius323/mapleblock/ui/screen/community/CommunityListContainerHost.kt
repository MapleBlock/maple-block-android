package com.choius323.mapleblock.ui.screen.community

import com.choius323.mapleblock.ui.model.CommunityListItem

data class CommunityListUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val communityList: List<CommunityListItem> = emptyList(),
)

sealed interface CommunityListUiEvent {
    data object WriteCommunityArticle : CommunityListUiEvent
    data class OnClickCommunityArticle(val articleId: Long) : CommunityListUiEvent
}

sealed interface CommunityListSideEffect {
    data object GoWriteCommunityArticle : CommunityListSideEffect
    data class GoCommunityArticle(val articleId: Long) : CommunityListSideEffect
}
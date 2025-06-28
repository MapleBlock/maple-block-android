package com.choius323.mapleblock.ui.screen.community

import com.choius323.mapleblock.ui.model.CommunityListItem

data class CommunityListUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val communityList: List<CommunityListItem> = emptyList(),
)

sealed interface CommunityListUiEvent {
    data object WriteCommunityArticle : CommunityListUiEvent
}

sealed interface CommunityListSideEffect {
    data object GoWriteCommunityArticle : CommunityListSideEffect
}
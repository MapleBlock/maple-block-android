package com.choius323.mapleblock.ui.screen.communityarticle

import com.choius323.mapleblock.ui.model.ArticleComment
import com.choius323.mapleblock.ui.model.CommunityArticle

data class CommunityArticleUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val article: CommunityArticle? = null,
    val comments: List<ArticleComment> = emptyList(),
    val newComment: String = "",
    val isBookmarked: Boolean = false,
)

sealed interface CommunityArticleUiEvent {
    data class OnCommentChange(val comment: String) : CommunityArticleUiEvent
    data object OnSubmitComment : CommunityArticleUiEvent
    data object OnBookmarkClick : CommunityArticleUiEvent
    data class OnLikeComment(val commentId: Long) : CommunityArticleUiEvent
    data object OnEditArticle : CommunityArticleUiEvent
    data object OnReportArticle : CommunityArticleUiEvent
}

sealed interface CommunityArticleSideEffect {
    data class ShowToast(val message: String) : CommunityArticleSideEffect
    // data object NavigateToDetailScreen : CommunityArticleSideEffect
}
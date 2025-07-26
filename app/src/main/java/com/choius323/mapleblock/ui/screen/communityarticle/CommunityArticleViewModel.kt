package com.choius323.mapleblock.ui.screen.communityarticle

import androidx.lifecycle.ViewModel
import com.choius323.mapleblock.ui.model.ArticleComment
import com.choius323.mapleblock.ui.model.CommunityArticle
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class CommunityArticleViewModel(
    // private val sendCommentUseCase: SendCommentUseCase,
    // private val getCommentsUseCase: GetCommentsUseCase
) : ViewModel(), ContainerHost<CommunityArticleUiState, CommunityArticleSideEffect> {

    override val container =
        container<CommunityArticleUiState, CommunityArticleSideEffect>(CommunityArticleUiState())

    init {
        loadArticle()
    }

    fun onEvent(event: CommunityArticleUiEvent) = when (event) {
        is CommunityArticleUiEvent.OnBookmarkClick -> onBookmarkClick()
        is CommunityArticleUiEvent.OnCommentChange -> onCommentChange(event.comment)
        is CommunityArticleUiEvent.OnSubmitComment -> onSubmitComment()
        is CommunityArticleUiEvent.OnLikeComment -> onLikeComment(event.commentId)
        is CommunityArticleUiEvent.OnReportArticle -> onReportArticle()
        is CommunityArticleUiEvent.OnEditArticle -> intent {
            // TODO: 게시글 수정 로직 추가
        }
    }

    private fun loadArticle() = intent {
        state.copy(
            article = CommunityArticle( // CommunityArticle 모델 사용
                id = 1,
                title = "오늘 큐브 돌리다가...",
                content = "레전드리 등급 가서 너무 기분 좋네요 ㅎㅎㅎ 다들 득템하세요!",
                author = "메이플고인물",
                viewCount = 123,
                commentCount = 3,
                likeCount = 10,
                timestamp = "2024.07.25",
                images = listOf(
                    "https://apis.map.kakao.com/android_v2/decal.gif",
                    "https://apis.map.kakao.com/android_v2/labelstyle_desc.png"
                )
            ), comments = listOf(
                ArticleComment.sample2, ArticleComment.sample3, ArticleComment.sample4
            ), isLoading = false
        )
    }

    fun onCommentChange(comment: String) = intent {
        reduce {
            state.copy(newComment = comment)
        }
    }

    fun onSubmitComment() = intent {
        // TODO: 댓글 등록 로직 (API 호출)

        // 성공 시 SideEffect 발생
        postSideEffect(CommunityArticleSideEffect.ShowToast("댓글이 등록되었습니다."))

        reduce {
            state.copy(newComment = "")
        }
    }

    fun onBookmarkClick() = intent {
        // TODO: 게시글 북마크 클릭 API 로직 추가
        reduce {
            state.copy(isBookmarked = state.isBookmarked.not())
        }
    }

    private fun onLikeComment(commentId: Long) = intent {
        // TODO: 댓글 좋아요 클릭 API 로직 추가
        reduce {
            state.copy(
                comments = state.comments.map { comment ->
                    if (comment.id == commentId) {
                        comment.copy(isLiked = comment.isLiked.not())
                    } else {
                        comment
                    }
                })
        }
    }

    private fun onReportArticle() = intent {
        // TODO: 신고 로직 추가
    }
}
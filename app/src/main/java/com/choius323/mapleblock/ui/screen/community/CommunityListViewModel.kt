package com.choius323.mapleblock.ui.screen.community

import androidx.lifecycle.ViewModel
import com.choius323.mapleblock.ui.model.CommunityListItem
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import org.threeten.bp.LocalDateTime

class CommunityListViewModel() : ViewModel(),
    ContainerHost<CommunityListUiState, CommunityListSideEffect> {
    override val container: Container<CommunityListUiState, CommunityListSideEffect> =
        container(CommunityListUiState())

    init {
        val sampleData = CommunityListItem(
            articleType = "자유",
            title = "렙 250 찍었는데 사람들이랑 파티는 언제쯤 가능함요?",
            content = "스토리밀고 계속 보스 혼자서 잡는데 남들이랑 파티해서 보스 잡는건 언제쯤 하나요 팔라딘 키우는디 팔라딘 시너지 좋아서 데려간다...",
            date = LocalDateTime.of(2025, 6, 24, 14, 5),
            commentsCount = 1234,
            likesCount = 1234,
            imageUrl = "",
            profileName = "메이플지박령",
            profileImageUrl = "",
        )
        intent {
            reduce {
                state.copy(communityList = List(5) { sampleData })
            }
        }
    }

    fun onEvent(event: CommunityListUiEvent) = when (event) {
        is CommunityListUiEvent.WriteCommunityArticle -> intent {
            postSideEffect(CommunityListSideEffect.GoWriteCommunityArticle)
        }
    }
}
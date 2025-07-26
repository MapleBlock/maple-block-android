package com.choius323.mapleblock.ui.screen.community

import androidx.lifecycle.ViewModel
import com.choius323.mapleblock.ui.model.CommunityListItem
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class CommunityListViewModel() : ViewModel(),
    ContainerHost<CommunityListUiState, CommunityListSideEffect> {
    override val container: Container<CommunityListUiState, CommunityListSideEffect> =
        container(CommunityListUiState())

    init {
        val sampleData = CommunityListItem.sample2
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
        is CommunityListUiEvent.OnClickCommunityArticle -> intent {
            postSideEffect(CommunityListSideEffect.GoCommunityArticle(event.articleId))
        }
    }
}
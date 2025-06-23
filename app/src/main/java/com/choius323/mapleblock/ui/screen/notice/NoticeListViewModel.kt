package com.choius323.mapleblock.ui.screen.notice

import androidx.lifecycle.ViewModel
import com.choius323.mapleblock.usecase.GetNoticeListUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class NoticeListViewModel(
    private val getNoticeListUseCase: GetNoticeListUseCase,
) : ViewModel(), ContainerHost<NoticeListUiState, NoticeListSideEffect> {
    override val container: Container<NoticeListUiState, NoticeListSideEffect> =
        container(NoticeListUiState())

    fun onEvent(event: NoticeListUiEvent) {
        when (event) {
            is NoticeListUiEvent.ClickNotice -> intent {
                postSideEffect(NoticeListSideEffect.GoNoticeArticleScreen(event.articleId))
            }

            is NoticeListUiEvent.LoadData -> loadData(event.type, event.isForceLoad)
        }
    }

    private fun loadData(type: String, isForceLoad: Boolean) = intent {
        if ((state.isLoaded && isForceLoad.not()) || state.isLoading) return@intent
        reduce { state.copy(isLoading = true) }
        getNoticeListUseCase(type).collect { result ->
            if (result.isSuccess) {
                reduce { state.copy(isLoading = false, noticeList = result.getOrThrow()) }
            } else {
                reduce { state.copy(isLoading = false) }
                postSideEffect(
                    NoticeListSideEffect.ShowToast(
                        result.exceptionOrNull()?.message ?: "Unknown error"
                    )
                )
            }
        }
    }
}
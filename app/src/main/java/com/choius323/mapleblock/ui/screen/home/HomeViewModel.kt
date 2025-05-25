package com.choius323.mapleblock.ui.screen.home

import androidx.lifecycle.ViewModel
import com.choius323.mapleblock.ui.model.Notice
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class HomeViewModel(
) : ContainerHost<HomeUiState, HomeSideEffect>, ViewModel() {
    override val container = container<HomeUiState, HomeSideEffect>(initialState = HomeUiState())

    fun add() = intent {
        postSideEffect(HomeSideEffect.ShowToast("add notice ${state.notices.size}"))
        reduce {
            state.copy(
                notices = state.notices + Notice(
                    "title ${state.notices.size}",
                    "content ${state.notices.size}"
                )
            )
        }
    }
}

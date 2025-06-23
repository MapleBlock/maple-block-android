package com.choius323.mapleblock.ui.screen.home

import com.choius323.mapleblock.ui.model.Notice

data class HomeUiState(
    val notices: List<Notice> = emptyList(),
)

sealed class HomeSideEffect {
    data class ShowToast(val message: String) : HomeSideEffect()
}
package com.choius323.mapleblock.ui.screen.imagepage

data class ImagePageUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val imageList: List<Any> = emptyList(),
    val initialPage: Int = 0,
)

sealed interface ImagePageUiEvent {
    data object OnClickBackButton : ImagePageUiEvent
}

sealed interface ImagePageSideEffect {
    data class ShowToast(val message: String) : ImagePageSideEffect
    data object GoBack : ImagePageSideEffect
}
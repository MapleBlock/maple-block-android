package com.choius323.mapleblock.ui.screen.writepost

data class WritePostUiState(
    val title: String = "",
    val content: String = "",
    val isLoading: Boolean = false,
)

sealed interface WritePostSideEffect {
    data object PostSuccess : WritePostSideEffect
    data class ShowToast(val message: String) : WritePostSideEffect
}

sealed interface WritePostIntent {
    data class OnTitleChange(val title: String) : WritePostIntent
    data class OnContentChange(val content: String) : WritePostIntent
    data object OnWritePost : WritePostIntent
}
package com.choius323.mapleblock.ui.screen.writepost

data class WritePostUiState(
    val title: String = "",
    val content: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val imageUrlStrList: List<String> = emptyList(),
) {
    companion object {
        const val MAX_IMAGE_COUNT: Int = 5
    }
}

sealed interface WritePostSideEffect {
    data object PostSuccess : WritePostSideEffect
    data class ShowToast(val message: String) : WritePostSideEffect
    data object SelectImages : WritePostSideEffect
    data object ShowPermissionDialog : WritePostSideEffect
    data object GoBack : WritePostSideEffect
}

sealed interface WritePostIntent {
    data class OnTitleChange(val title: String) : WritePostIntent
    data class OnContentChange(val content: String) : WritePostIntent
    data class SetImageUrlStr(val strList: List<String>) : WritePostIntent
    data object OnBackPressed : WritePostIntent
    data object OnWritePost : WritePostIntent
    data object SelectImages : WritePostIntent
}
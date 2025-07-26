package com.choius323.mapleblock.ui.screen.imagepage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.choius323.mapleblock.ui.navigation.NavItem
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class ImagePageViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel(), ContainerHost<ImagePageUiState, ImagePageSideEffect> {
    override val container: Container<ImagePageUiState, ImagePageSideEffect> =
        container(ImagePageUiState())

    init {
        intent {
            val (index, imageList) = savedStateHandle.toRoute<NavItem.ImagePage>()
            println(index)
            reduce {
                state.copy(imageList = imageList, initialPage = index)
            }
        }
    }

    fun onEvent(event: ImagePageUiEvent) = when (event) {
        is ImagePageUiEvent.OnClickBackButton -> intent {
            postSideEffect(ImagePageSideEffect.GoBack)
        }
    }

    companion object {
        const val IMAGE_LIST_KEY = "imageList"
    }
}
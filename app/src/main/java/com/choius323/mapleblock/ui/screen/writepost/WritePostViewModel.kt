package com.choius323.mapleblock.ui.screen.writepost

import androidx.lifecycle.ViewModel
import com.choius323.mapleblock.usecase.WritePostUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class WritePostViewModel(
    private val writePostUseCase: WritePostUseCase,
) : ViewModel(), ContainerHost<WritePostUiState, WritePostSideEffect> {
    override val container: Container<WritePostUiState, WritePostSideEffect> =
        container(WritePostUiState())

    fun onEvent(intent: WritePostIntent) = when (intent) {
        is WritePostIntent.OnTitleChange -> onTitleChanged(intent)
        is WritePostIntent.OnContentChange -> onContentChanged(intent)
        is WritePostIntent.OnWritePost -> onWritePost()
        is WritePostIntent.OnBackPressed -> intent {
            postSideEffect(WritePostSideEffect.GoBack)
        }

        is WritePostIntent.SetImageUrlStr -> setImageUrlStr(intent)
        is WritePostIntent.SelectImages -> intent {
            postSideEffect(WritePostSideEffect.SelectImages)
        }

        is WritePostIntent.ImageDelete -> intent {
            reduce {
                println(intent.index)
                println(state.imageUrlStrList)
                println(state.imageUrlStrList.filterIndexed { index, _ -> index != intent.index })
                state.copy(imageUrlStrList = state.imageUrlStrList.filterIndexed { index, _ ->
                    println(
                        "$index ${intent.index}"
                    );index != intent.index
                })
            }
        }
    }

    private fun onTitleChanged(intent: WritePostIntent.OnTitleChange) = intent {
        reduce {
            state.copy(title = intent.title)
        }
    }

    private fun onContentChanged(intent: WritePostIntent.OnContentChange) = intent {
        reduce {
            state.copy(content = intent.content)
        }
    }

    private fun onWritePost() = intent {
        reduce {
            state.copy(isLoading = true)
        }

        writePostUseCase(state.title, state.content).collect { result ->
            if (result.isSuccess) {
                postSideEffect(WritePostSideEffect.PostSuccess)
                reduce {
                    state.copy(isLoading = false)
                }
            } else {
                postSideEffect(
                    WritePostSideEffect.ShowToast(
                        result.exceptionOrNull()?.message ?: "Unknown error"
                    )
                )
                reduce {
                    state.copy(isLoading = false)
                }
            }
        }
    }

    private fun setImageUrlStr(intent: WritePostIntent.SetImageUrlStr) = intent {
        reduce {
            state.copy(imageUrlStrList = intent.strList)
        }
    }
}

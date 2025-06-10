package com.choius323.mapleblock.ui.screen.writepost

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.choius323.mapleblock.ui.component.MBText
import com.choius323.mapleblock.ui.component.MBTextField
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun WritePostScreen(
    modifier: Modifier = Modifier,
    viewModel: WritePostViewModel = koinViewModel(),
    onSuccessPost: () -> Unit = {},
) {
    val uiState: WritePostUiState by viewModel.collectAsState()
    val context = LocalContext.current
    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is WritePostSideEffect.PostSuccess -> onSuccessPost()
            is WritePostSideEffect.ShowToast -> Toast.makeText(
                context,
                sideEffect.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    WritePostScreen(
        modifier = modifier,
        uiState = uiState,
        onIntent = viewModel::onIntent,
    )
}

@Composable
fun WritePostScreen(
    modifier: Modifier = Modifier,
    uiState: WritePostUiState,
    onIntent: (WritePostIntent) -> Unit,
) {
    if (uiState.isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    WritePostBody(
        title = uiState.title,
        content = uiState.content,
        onIntent = onIntent,
        modifier = modifier,
    )
}

@Composable
private fun WritePostBody(
    modifier: Modifier = Modifier,
    title: String = "",
    content: String = "",
    onIntent: (WritePostIntent) -> Unit,
) {
    Column(modifier) {
        MBTextField(
            value = title,
            onValueChange = { onIntent(WritePostIntent.OnTitleChange(title)) },
            hint = "제목을 입력하세요",
            singleLine = true,
        )
        MBTextField(
            value = content,
            onValueChange = { onIntent(WritePostIntent.OnContentChange(content)) },
            hint = "내용을 입력하세요",
        )
        Button({ onIntent(WritePostIntent.OnWritePost) }) {
            MBText(text = "등록")
        }
    }
}
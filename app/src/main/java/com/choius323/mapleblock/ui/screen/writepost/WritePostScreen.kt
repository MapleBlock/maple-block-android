package com.choius323.mapleblock.ui.screen.writepost

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.choius323.mapleblock.ui.component.MBText
import com.choius323.mapleblock.ui.component.MBTextField
import com.choius323.mapleblock.ui.component.ProvideAppBar
import com.choius323.mapleblock.ui.theme.MBTheme
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
    ProvideAppBar(
        showAppBar = true,
        navigationIcon = {
            Icon(
                Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "",
                Modifier.clickable(onClick = onSuccessPost)
            )
        }
    )
    if (uiState.isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    WritePostScreenContent(
        modifier = modifier,
        uiState = uiState,
        onEvent = viewModel::onEvent,
    )
}

@Composable
private fun WritePostScreenContent(
    uiState: WritePostUiState,
    modifier: Modifier = Modifier,
    onEvent: (WritePostIntent) -> Unit,
) {
    Column(modifier) {
        MBTextField(
            value = uiState.title,
            onValueChange = { onEvent(WritePostIntent.OnTitleChange(uiState.title)) },
            hint = "제목을 입력하세요",
            singleLine = true,
        )
        MBTextField(
            value = uiState.content,
            onValueChange = { onEvent(WritePostIntent.OnContentChange(uiState.content)) },
            hint = "내용을 입력하세요",
        )
        Button({ onEvent(WritePostIntent.OnWritePost) }) {
            MBText(text = "등록")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WritePostScreenContentPreview() {
    MBTheme {
        Surface {
            WritePostScreen(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
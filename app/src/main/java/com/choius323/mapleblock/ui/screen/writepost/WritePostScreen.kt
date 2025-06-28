package com.choius323.mapleblock.ui.screen.writepost

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddToPhotos
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil3.compose.AsyncImage
import com.choius323.mapleblock.ui.component.HandlePermissionActions
import com.choius323.mapleblock.ui.component.MBText
import com.choius323.mapleblock.ui.component.MBTextField
import com.choius323.mapleblock.ui.component.ProvideAppBar
import com.choius323.mapleblock.ui.component.getImagePicker
import com.choius323.mapleblock.ui.theme.MBTheme
import com.choius323.mapleblock.util.readImagePermissions
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WritePostScreen(
    modifier: Modifier = Modifier,
    viewModel: WritePostViewModel = koinViewModel(),
    goBack: () -> Unit = {},
    onSuccessPost: () -> Unit = {},
) {
    val uiState: WritePostUiState by viewModel.collectAsState()
    val context = LocalContext.current
    val showPermissionDialog = remember { mutableStateOf(false) }
    val permissionState = rememberMultiplePermissionsState(permissions = readImagePermissions)
    val imagePicker = getImagePicker(WritePostUiState.MAX_IMAGE_COUNT) { list ->
        Log.d(TAG, "list: $list")
        viewModel.onEvent(WritePostIntent.SetImageUrlStr(list.mapNotNull { it?.toString() }))
    }
    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is WritePostSideEffect.PostSuccess -> onSuccessPost()
            is WritePostSideEffect.ShowToast -> Toast.makeText(
                context,
                sideEffect.message,
                Toast.LENGTH_SHORT
            ).show()

            is WritePostSideEffect.ShowPermissionDialog -> showPermissionDialog.value = true
            is WritePostSideEffect.GoBack -> onSuccessPost()
            is WritePostSideEffect.SelectImages -> {
                if (permissionState.allPermissionsGranted) {
                    imagePicker()
                } else {
                    showPermissionDialog.value = true
                }
            }
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
    HandlePermissionActions(
        permissionState,
        showPermissionDialog,
        onPermissionAllGranted = {
            if (permissionState.allPermissionsGranted) {
                imagePicker()
            } else {
                showPermissionDialog.value = true
            }
        }
    )
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
        ImageAttachment(
            uiState.imageUrlStrList,
            onEvent = onEvent
        )
        Button({ onEvent(WritePostIntent.OnWritePost) }) {
            MBText(text = "게시하기")
        }
    }
}

@Composable
private fun ImageAttachment(
    imageStrList: List<String>,
    modifier: Modifier = Modifier,
    onEvent: (WritePostIntent) -> Unit,
) {
    val imageModifier = remember {
        Modifier
            .size(64.dp)
            .border(1.dp, Color.Black)
    }
    val scrollState = rememberScrollState()
    val imageUriList by remember(imageStrList) {
        derivedStateOf {
            imageStrList.map(String::toUri)
        }
    }
    Column(
        modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MBText("사진첨부")
            MBText("${imageStrList.size}/${WritePostUiState.MAX_IMAGE_COUNT}")
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.horizontalScroll(scrollState)
        ) {
            repeat(WritePostUiState.MAX_IMAGE_COUNT) {
                val uri = imageUriList.getOrNull(it)
                if (uri == null) {
                    Icon(
                        Icons.Default.AddToPhotos,
                        "사진 첨부 버튼",
                        modifier = imageModifier
                            .clickable { onEvent(WritePostIntent.SelectImages) }
                    )
                } else {
                    AsyncImage(
                        model = uri,
                        contentDescription = null,
                        modifier = imageModifier
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WritePostScreenContentPreview() {
    MBTheme {
        Surface {
            WritePostScreenContent(
                modifier = Modifier.fillMaxSize(),
                uiState = WritePostUiState(),
                onEvent = {},
            )
        }
    }
}

private const val TAG = "WritePostScreen"
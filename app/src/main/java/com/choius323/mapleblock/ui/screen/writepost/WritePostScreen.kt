package com.choius323.mapleblock.ui.screen.writepost

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.choius323.mapleblock.ui.component.HandlePermissionActions
import com.choius323.mapleblock.ui.component.MBButton
import com.choius323.mapleblock.ui.component.MBChip
import com.choius323.mapleblock.ui.component.MBHorizonDivider
import com.choius323.mapleblock.ui.component.MBText
import com.choius323.mapleblock.ui.component.MBTextFieldSection
import com.choius323.mapleblock.ui.component.ProvideAppBar
import com.choius323.mapleblock.ui.component.getImagePicker
import com.choius323.mapleblock.ui.icon.Back
import com.choius323.mapleblock.ui.icon.MBIcons
import com.choius323.mapleblock.ui.theme.MBTheme
import com.choius323.mapleblock.ui.theme.MBTypo
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
            is WritePostSideEffect.GoBack -> goBack()
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
                MBIcons.Pixel.Back,
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
    val scrollState = rememberScrollState()
    Column(
        modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(scrollState)
    ) {
        CategorySection(
            modifier = Modifier.fillMaxWidth(),
            categoryList = listOf("가이드", "자유"),
            selectedCategoryIndex = 0,
            onCategoryClick = { }
        )
        MBHorizonDivider()
        MBTextFieldSection(
            sectionType = "제목",
            inputText = uiState.title,
            onTextChange = { onEvent(WritePostIntent.OnTitleChange(it)) },
            modifier = Modifier.fillMaxSize(),
            isEssential = true,
            information = "30자 이내로 입력해주세요."
        )
        MBHorizonDivider()
        MBTextFieldSection(
            sectionType = "본문",
            inputText = uiState.content,
            onTextChange = { onEvent(WritePostIntent.OnContentChange(it)) },
            modifier = Modifier.fillMaxSize(),
            isEssential = true,
            singleLine = false,
            information = "30자 이내로 입력해주세요.",
            textFieldModifier = Modifier
                .fillMaxWidth()
                .heightIn(290.dp)
        )
        MBHorizonDivider()
        ImageAttachmentRow(
            uiState.imageUrlStrList,
            onEvent = onEvent
        )
        MBHorizonDivider()
        Spacer(Modifier.height(16.dp))
        MBButton(
            onClick = { onEvent(WritePostIntent.OnWritePost) },
            modifier = Modifier.fillMaxWidth(),
            text = "게시하기"
        )
    }
}

@Composable
fun CategorySection(
    categoryList: List<String>,
    selectedCategoryIndex: Int,
    modifier: Modifier = Modifier,
    onCategoryClick: (Int) -> Unit,
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            MBText(text = "카테고리 선택", style = MBTypo.Subtitle2)
            Box(
                Modifier
                    .align(Alignment.Top)
                    .padding(top = 3.dp, start = 2.67.dp)
                    .size(4.dp)
                    .rotate(45f)
                    .background(MaterialTheme.colorScheme.error)
            )
        }
        Spacer(Modifier.weight(1f))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            for ((index, category) in categoryList.withIndex()) {
                MBChip(
                    text = category,
                    isSelected = selectedCategoryIndex == index,
                    onClick = { onCategoryClick(index) }
                )
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
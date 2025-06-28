package com.choius323.mapleblock.ui.screen.noticearticle

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.svg.SvgDecoder
import com.choius323.mapleblock.R
import com.choius323.mapleblock.ui.component.HandlePermissionActions
import com.choius323.mapleblock.ui.component.getImagePicker
import com.choius323.mapleblock.ui.screen.home.component.ArticleOverview
import com.choius323.mapleblock.util.readImagePermissions
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NoticeArticleScreen(
    noticeId: Int,
    modifier: Modifier = Modifier,
) {
    var imageUris = remember { mutableStateListOf<Uri>() }
    var showPermissionDialog = remember { mutableStateOf(false) }
    val permissionState = rememberMultiplePermissionsState(permissions = readImagePermissions)
    Column(modifier) {
        Text(text = "NoticeArticle Screen", modifier = Modifier.padding(20.dp))
        ArticleOverview(
            "Title1",
            "2025-05-05T00:00:00+01:00",
            Modifier,
            "https://i.ytimg.com/vi/k90y6PIzIaE/hqdefault.jpg"
        )
        val imagePicker = getImagePicker(10) {
            imageUris.clear()
            // imageUris.addAll(it)
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
        Button({
            if (permissionState.allPermissionsGranted) {
                imagePicker()
            } else {
                showPermissionDialog.value = true
            }
        }) {
            Text("Select Image")
        }
        LazyVerticalGrid(GridCells.Fixed(2)) {
            items(imageUris, { it.path!! }) { imageUri ->
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUri)
                        .decoderFactory(SvgDecoder.Factory())
                        .build(),
                    contentDescription = imageUri.path,
                    modifier = Modifier
                        .size(width = 160.dp, height = 160.dp)
                        .aspectRatio(1f / 1f, matchHeightConstraintsFirst = true),
                    error = painterResource(R.mipmap.ic_launcher_foreground)
                )
            }
        }
    }
}

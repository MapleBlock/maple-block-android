package com.choius323.mapleblock.ui.component

import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

@Composable
fun getImagePicker(
    maxImageCount: Int,
    onCancel: () -> Unit = {},
    onImagePicked: (List<Uri?>) -> Unit,
): () -> Unit {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
        val launcher = rememberLauncherForActivityResult(
            ActivityResultContracts.PickMultipleVisualMedia(maxImageCount)
        ) { uris ->
            Log.d("ImagePicker", "SDK 34+ Multiple Picker - Selected items: ${uris.size}")
            if (uris.isNotEmpty()) {
                onImagePicked(uris)
            } else {
                onCancel()
            }
        }
        return {
            launcher.launch(
                PickVisualMediaRequest(
                    ActivityResultContracts.PickVisualMedia.ImageAndVideo
                )
            )
        }
    } else {
        val launcher = rememberLauncherForActivityResult(
            ActivityResultContracts.GetMultipleContents()
        ) { uris ->
            Log.d("ImagePicker", "Legacy Multiple Picker - Selected items: ${uris.size}")
            if (uris.isNotEmpty()) {
                onImagePicked(uris.take(maxImageCount))
            } else {
                onCancel()
            }
        }
        return { launcher.launch("image/*") }
    }
}
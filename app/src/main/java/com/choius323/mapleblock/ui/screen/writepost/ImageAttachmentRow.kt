package com.choius323.mapleblock.ui.screen.writepost

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil3.compose.AsyncImage
import com.choius323.mapleblock.ui.component.MBText
import com.choius323.mapleblock.ui.icon.AddPicture
import com.choius323.mapleblock.ui.icon.Delete
import com.choius323.mapleblock.ui.icon.MBIcons
import com.choius323.mapleblock.ui.theme.MBColor
import com.choius323.mapleblock.ui.theme.MBTheme
import com.choius323.mapleblock.ui.theme.MBTypo

@Composable
fun ImageAttachmentRow(
    imageStrList: List<String>,
    modifier: Modifier = Modifier,
    onEvent: (WritePostIntent) -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier.padding(vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MBText("사진첨부", style = MBTypo.Subtitle2)
            MBText(
                "${imageStrList.size}/${WritePostUiState.MAX_IMAGE_COUNT}",
                color = MBColor.Gray400,
                style = MBTypo.Caption,
            )
        }
        Spacer(Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.horizontalScroll(scrollState)
        ) {
            repeat(WritePostUiState.MAX_IMAGE_COUNT) { index ->
                val str = imageStrList.getOrNull(index)
                if (str == null) {
                    ImageEmpty(onEvent = onEvent)
                } else {
                    ImageAttachment(str, index, onEvent = onEvent)
                }
            }
        }
    }
}

@Composable
private fun ImageEmpty(
    modifier: Modifier = Modifier,
    onEvent: (WritePostIntent) -> Unit,
) {
    Box(
        modifier
            .size(64.dp)
            .border(1.dp, MaterialTheme.colorScheme.outline)
            .clickable { onEvent(WritePostIntent.SelectImages) },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            MBIcons.Pixel.AddPicture,
            "사진 첨부 버튼",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ImageAttachment(
    imageStr: String,
    index: Int,
    modifier: Modifier = Modifier,
    onEvent: (WritePostIntent) -> Unit,
) {
    val uri by remember(imageStr) { derivedStateOf { imageStr.toUri() } }
    Box(
        modifier = modifier
            .size(64.dp)
            .border(1.dp, MaterialTheme.colorScheme.outline)
            .clickable(onClick = { onEvent(WritePostIntent.ImageDelete(index)) }),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = uri,
            contentDescription = "Deletable Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MBColor.Black.copy(alpha = 0.4f))
        )
        Icon(
            imageVector = MBIcons.Pixel.Delete,
            contentDescription = "Delete",
            tint = MBColor.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview
@Composable
private fun ImageAttachmentPreview() {
    MBTheme {
        Surface {
            ImageAttachment(
                imageStr = "",
                index = 0,
                modifier = Modifier.background(MBColor.Error),
                onEvent = {},
            )
        }
    }
}
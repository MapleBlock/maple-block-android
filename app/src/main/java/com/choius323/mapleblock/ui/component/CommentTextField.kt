package com.choius323.mapleblock.ui.component

import androidx.annotation.FloatRange
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.choius323.mapleblock.R
import com.choius323.mapleblock.ui.icon.Add
import com.choius323.mapleblock.ui.icon.MBIcons
import com.choius323.mapleblock.ui.icon.Send
import com.choius323.mapleblock.ui.theme.MBColor
import com.choius323.mapleblock.ui.theme.MBTheme
import com.choius323.mapleblock.ui.theme.MBTypo

@Composable
fun MBCommentTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onSend: () -> Unit,
    modifier: Modifier = Modifier,
    singleLine: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    hint: String = "댓글을 입력하세요.",
    imageModel: Any? = null,
    onAddImage: () -> Unit = {},
    onRemoveImage: () -> Unit = {},
) {
    var textFieldHeight by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    val edgeColor = MaterialTheme.colorScheme.surface
    var lineBottom by remember { mutableIntStateOf(Int.MAX_VALUE) }

    Row(
        modifier = modifier, verticalAlignment = Alignment.Top
    ) {
        AsyncImage(
            model = imageModel ?: R.drawable.img_checker,
            contentDescription = "첨부된 이미지",
            modifier = Modifier
                .padding(top = 6.dp, end = 10.dp)
                .size(40.dp),
            contentScale = ContentScale.Crop
        )

        // 텍스트 필드 영역
        Row(
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = MBColor.Black,
                    shape = RectangleShape,
                )
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 이미지 추가 버튼
            IconButton(onClick = onAddImage, modifier = Modifier.size(24.dp)) {
                Icon(
                    imageVector = MBIcons.Pixel.Add,
                    contentDescription = "이미지 추가",
                    tint = MBColor.Gray300
                )
            }
            Spacer(modifier = Modifier.size(4.dp))
            // 텍스트 필드와 힌트
            Box(
                Modifier
                    .weight(1f)
                    .staticVerticalFadingEdge(edgeColor, textFieldHeight)
            ) {
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(textFieldHeight)
                        .verticalScroll(rememberScrollState()),
                    textStyle = MBTypo.Body2,
                    singleLine = singleLine,
                    keyboardOptions = keyboardOptions,
                    decorationBox = { innerTextField ->
                        if (value.isEmpty()) {
                            MBText(
                                text = hint,
                                color = MBColor.Gray400,
                                style = MBTypo.Body2,
                            )
                        }
                        innerTextField()
                    },
                    onTextLayout = { result ->
                        val newLineBottom = minOf(result.lineCount, 2) - 1
                        if (newLineBottom != lineBottom) {
                            lineBottom = newLineBottom
                            val newHeightInPx = result.getLineBottom(lineBottom)

                            // 계산된 픽셀 높이를 Dp로 변환하여 상태 업데이트
                            val newHeightInDp = with(density) { newHeightInPx.toDp() }

                            if (textFieldHeight != newHeightInDp) {
                                textFieldHeight = maxOf(newHeightInDp, textFieldHeight)
                            }
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.width(24.dp))
            // 전송 버튼
            IconButton(
                onClick = onSend,
                enabled = value.isNotEmpty(),
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = MBIcons.Pixel.Send,
                    contentDescription = "전송",
                    tint = if (value.isNotEmpty()) MBColor.Gray500 else MBColor.Gray300
                )
            }
        }
    }
}

/**
 * 컨텐츠의 상단과 하단에 항상 고정된 그라데이션 페이드 효과를 적용합니다.
 *
 * @param range 그라데이션의 범위를 지정합니다.
 * @param edgeColor 그라데이션의 바깥쪽 색상입니다. 일반적으로 배경색과 일치시킵니다.
 * @param height 컨텐츠 높이를 Dp 단위로 지정합니다.
 */
private fun Modifier.staticVerticalFadingEdge(
    edgeColor: Color,
    height: Dp?,
    @FloatRange(from = 0.0, 1.0) range: Float = 0.15f,
): Modifier = this
    .drawWithContent {
        drawContent()
        val contentHeight = height?.toPx() ?: size.height
        val fadingEdgeBrush = Brush.verticalGradient(
            0f to edgeColor,
            range to MBColor.Transparent,
            1f - range to MBColor.Transparent,
            1f to edgeColor,
            startY = 0f,
            endY = contentHeight,
        )

        drawRect(
            brush = fadingEdgeBrush,
        )
    }

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun MBCommentTextFieldPreview() {
    val text = "댓글입니다."
    val longText = "두 줄 이상 넘어가는 긴 텍스트입니다.\n스크롤이 잘 되는지 확인해봅시다. \n두 번째 줄입니다."

    MBTheme {
        Surface {
            Column(Modifier.padding(16.dp)) {
                // 기본 상태
                MBCommentTextField(value = "", onValueChange = {}, onSend = {})
                Spacer(Modifier.height(16.dp))
                // 텍스트 입력 상태
                MBCommentTextField(value = "댓글입니다.", onValueChange = {}, onSend = {})
                Spacer(Modifier.height(16.dp))
                // 긴 텍스트 (스크롤) 상태
                MBCommentTextField(value = longText, onValueChange = {}, onSend = {})
                Spacer(Modifier.height(16.dp))
                // 이미지 첨부 상태
                MBCommentTextField(
                    value = text,
                    onValueChange = {},
                    onSend = {},
                    imageModel = null,
                    onRemoveImage = {}
                )
            }
        }
    }
}
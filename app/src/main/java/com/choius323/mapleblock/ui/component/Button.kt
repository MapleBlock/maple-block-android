package com.choius323.mapleblock.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.choius323.mapleblock.ui.icon.ArrowDropDown
import com.choius323.mapleblock.ui.icon.Home
import com.choius323.mapleblock.ui.icon.MBIcons
import com.choius323.mapleblock.ui.theme.Gray90
import com.choius323.mapleblock.ui.theme.MBColor
import com.choius323.mapleblock.ui.theme.MBTheme
import com.choius323.mapleblock.ui.theme.MBTypo

@Composable
fun MBButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    MBButton(
        content = { MBText(text, style = MBTypo.Subtitle2) },
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
    )
}

@Composable
fun MBButton(
    content: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val borderColor = if (enabled) MaterialTheme.colorScheme.onSurface else MBColor.Gray300
    Button(
        onClick = onClick,
        modifier = modifier
            .drawBehind {
                if (enabled) {
                    val strokeWidth = 2.dp.toPx()
                    drawLine(
                        color = borderColor,
                        start = Offset(0f, -2 + size.height - strokeWidth),
                        end = Offset(size.width, -2 + size.height - strokeWidth),
                        strokeWidth = strokeWidth
                    )
                }
            },
        enabled = enabled,
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MBColor.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = MBColor.Transparent,
            disabledContentColor = MBColor.Gray300,
        ),
        content = content,
        border = BorderStroke(2.dp, borderColor),
    )
}

// 5.1 버튼(Button) 컴포넌트 [cite: 10]
@Composable
fun MBPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .heightIn(min = 48.dp), // 최소 높이를 위한 임의 설정 (패딩 12px * 2 + 폰트 16px = 40px, 여기에 여유 줌)
        enabled = enabled,
        shape = MaterialTheme.shapes.small, // border-radius: 6px [cite: 11] -> MaterialTheme.shapes.small (6.dp)
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary, // background-color: #007AFF [cite: 11]
            contentColor = MaterialTheme.colorScheme.onPrimary, // color: #FFFFFF [cite: 11]
            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f), // Disabled: opacity 50% [cite: 13]
            disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
        ),
        contentPadding = PaddingValues(
            horizontal = 24.dp,
            vertical = 12.dp
        ) // padding: 12px 24px [cite: 11]
    ) {
        MBText(
            text = text,
            style = MaterialTheme.typography.labelLarge // font-size: 16px, font-weight: 600 [cite: 11]
        )
    }
}

@Composable
fun MBSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    OutlinedButton( // OutlinedButton을 사용하여 테두리 스타일 적용
        onClick = onClick,
        modifier = modifier
            .heightIn(min = 48.dp), // 최소 높이를 위한 임의 설정
        enabled = enabled,
        shape = MaterialTheme.shapes.small, // border-radius: 6px [cite: 12] -> MaterialTheme.shapes.small (6.dp)
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Gray90, // background-color: #F5F5F7 [cite: 12]
            contentColor = MaterialTheme.colorScheme.primary, // color: #007AFF [cite: 12]
            disabledContainerColor = Gray90.copy(alpha = 0.5f), // Disabled: opacity 50% [cite: 13]
            disabledContentColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
        ),
        border = ButtonDefaults.outlinedButtonBorder(true).copy(
            // border: 1px solid #007AFF [cite: 12]
            brush = SolidColor(MaterialTheme.colorScheme.primary),
            width = 1.dp,
        ),
        contentPadding = PaddingValues(
            horizontal = 24.dp,
            vertical = 12.dp
        ) // padding: 12px 24px [cite: 12]
    ) {
        MBText(
            text = text,
            style = MaterialTheme.typography.labelLarge // font-size: 16px, font-weight: 600 [cite: 12]
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MapleButtonPreview() {
    MBTheme {
        Surface {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                MBButton(
                    content = {
                        Icon(MBIcons.Pixel.Home, contentDescription = null)
                        MBText(text = "기본 버튼", style = MBTypo.Subtitle2)
                        Icon(MBIcons.Pixel.ArrowDropDown, contentDescription = null)
                    },
                    modifier = Modifier,
                    onClick = {}
                )
                MBButton(
                    content = {
                        Icon(MBIcons.Pixel.Home, contentDescription = null)
                        MBText(text = "비활성화 버튼", style = MBTypo.Subtitle2)
                        Icon(MBIcons.Pixel.ArrowDropDown, contentDescription = null)
                    },
                    modifier = Modifier,
                    onClick = {},
                    enabled = false
                )
                MBPrimaryButton(text = "기본 버튼", onClick = { /* TODO */ })
                MBPrimaryButton(text = "비활성화 버튼", onClick = { /* TODO */ }, enabled = false)
                MBSecondaryButton(text = "보조 버튼", onClick = { /* TODO */ })
                MBSecondaryButton(text = "보조 비활성화 버튼", onClick = { /* TODO */ }, enabled = false)
            }
        }
    }
}
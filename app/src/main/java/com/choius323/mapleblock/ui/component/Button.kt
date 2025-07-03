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
            .heightIn(min = 48.dp),
        enabled = enabled,
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
            disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
        ),
        contentPadding = PaddingValues(
            horizontal = 24.dp,
            vertical = 12.dp
        )
    ) {
        MBText(
            text = text,
            style = MBTypo.Subtitle2
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
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .heightIn(min = 48.dp),
        enabled = enabled,
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Gray90,
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = Gray90.copy(alpha = 0.5f),
            disabledContentColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
        ),
        border = ButtonDefaults.outlinedButtonBorder(true).copy(
            brush = SolidColor(MaterialTheme.colorScheme.primary),
            width = 1.dp,
        ),
        contentPadding = PaddingValues(
            horizontal = 24.dp,
            vertical = 12.dp
        )
    ) {
        MBText(
            text = text,
            style = MBTypo.Subtitle2,
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
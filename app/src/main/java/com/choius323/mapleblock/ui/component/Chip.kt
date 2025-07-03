package com.choius323.mapleblock.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.choius323.mapleblock.R
import com.choius323.mapleblock.ui.theme.MBTheme
import com.choius323.mapleblock.ui.theme.MBTypo

@Composable
fun MBChip(
    text: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
) {
    val borderColor = MaterialTheme.colorScheme.onSurface
    Row(
        modifier = modifier
            .border(
                1.dp, borderColor, RectangleShape
            )
            .drawBehind {
                if (isSelected) {
                    val strokeWidth = 2.dp.toPx()
                    drawLine(
                        color = borderColor,
                        start = Offset(0f, size.height), // 왼쪽 하단
                        end = Offset(size.width, size.height), // 오른쪽 하단
                        strokeWidth = strokeWidth
                    )
                }
            }
            .clickable(onClick = onClick)
            .padding(vertical = 4.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isSelected) {
            Icon(
                painterResource(R.drawable.ic_checkbox),
                "Checked Chip",
                Modifier.size(16.dp),
            )
            Spacer(Modifier.width(4.dp))
        }
        MBText(
            text = text,
            style = MBTypo.Caption,
        )
    }
}

@Preview
@Composable
private fun MBChipPreview() {
    MBTheme {
        Surface {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .background(Color.Gray)
                    .padding(8.dp),
            ) {
                MBChip(text = "Chip")
                MBChip(text = "Chip Selected", isSelected = true)
            }
        }
    }
}
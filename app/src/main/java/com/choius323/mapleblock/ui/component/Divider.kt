package com.choius323.mapleblock.ui.component

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.choius323.mapleblock.ui.theme.MBColor

@Composable
fun MBHorizonDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = 2.dp,
    color: Color = MBColor.Gray200,
) {
    HorizontalDivider(
        thickness = thickness,
        color = color,
        modifier = modifier
    )
}
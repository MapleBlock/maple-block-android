package com.choius323.mapleblock.ui.icon

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private var _Send: ImageVector? = null
val MBIcons.Pixel.Send: ImageVector
    get() {
        if (_Send != null) return _Send!!
        _Send = ImageVector.Builder(
            name = "MBIcons.Pixel.Send",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(
                    22.0f,
                    2.0f
                ); horizontalLineTo(16.0f); verticalLineTo(4.0f); horizontalLineTo(22.0f); verticalLineTo(
                2.0f
            ); close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(
                    22.0f,
                    4.0f
                ); horizontalLineTo(20.0f); verticalLineTo(8.0f); horizontalLineTo(22.0f); verticalLineTo(
                4.0f
            ); close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(
                    16.0f,
                    4.0f
                ); horizontalLineTo(10.0f); verticalLineTo(6.0f); horizontalLineTo(16.0f); verticalLineTo(
                4.0f
            ); close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(
                    18.0f,
                    8.0f
                ); verticalLineTo(14.0f); horizontalLineTo(20.0f); verticalLineTo(8.0f); horizontalLineTo(
                18.0f
            ); close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(
                    16.0f,
                    10.0f
                ); verticalLineTo(20.0f); horizontalLineTo(18.0f); verticalLineTo(10.0f); horizontalLineTo(
                16.0f
            ); close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(
                    14.0f,
                    12.0f
                ); verticalLineTo(20.0f); horizontalLineTo(16.0f); verticalLineTo(12.0f); horizontalLineTo(
                14.0f
            ); close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(
                    12.0f,
                    14.0f
                ); verticalLineTo(20.0f); horizontalLineTo(14.0f); verticalLineTo(14.0f); horizontalLineTo(
                12.0f
            ); close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(
                    4.0f,
                    8.0f
                ); horizontalLineTo(2.0f); verticalLineTo(12.0f); horizontalLineTo(4.0f); verticalLineTo(
                8.0f
            ); close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(
                    16.0f,
                    8.0f
                ); horizontalLineTo(14.0f); verticalLineTo(10.0f); horizontalLineTo(16.0f); verticalLineTo(
                8.0f
            ); close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(
                    18.0f,
                    6.0f
                ); horizontalLineTo(16.0f); verticalLineTo(8.0f); horizontalLineTo(18.0f); verticalLineTo(
                6.0f
            ); close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(
                    20.0f,
                    4.0f
                ); horizontalLineTo(18.0f); verticalLineTo(6.0f); horizontalLineTo(20.0f); verticalLineTo(
                4.0f
            ); close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(14.0f, 10.0f); horizontalLineTo(
                12.0f
            ); verticalLineTo(12.0f); horizontalLineTo(14.0f); verticalLineTo(10.0f); close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(16.0f, 20.0f); horizontalLineTo(
                12.0f
            ); verticalLineTo(22.0f); horizontalLineTo(16.0f); verticalLineTo(20.0f); close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(12.0f, 14.0f); horizontalLineTo(
                10.0f
            ); verticalLineTo(20.0f); horizontalLineTo(12.0f); verticalLineTo(14.0f); close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(
                    12.0f,
                    12.0f
                ); horizontalLineTo(4.0f); verticalLineTo(14.0f); horizontalLineTo(12.0f); verticalLineTo(
                12.0f
            ); close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(
                    10.0f,
                    6.0f
                ); horizontalLineTo(4.0f); verticalLineTo(8.0f); horizontalLineTo(10.0f); verticalLineTo(
                6.0f
            ); close()
            }
        }.build()
        return _Send!!
    }

@Preview
@Composable
private fun SendPreview() {
    Icon(MBIcons.Pixel.Send, contentDescription = null)
}
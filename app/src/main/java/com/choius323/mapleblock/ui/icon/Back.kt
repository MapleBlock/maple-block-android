package com.choius323.mapleblock.ui.icon

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private var _back: ImageVector? = null
val MBIcons.Pixel.Back: ImageVector
    get() {
        if (_back != null) return _back!!
        _back = ImageVector.Builder(
            name = "MBIcons.Pixel.Back",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(9.0f, 3.0f)
                lineTo(11.0f, 3.0f)
                lineTo(11.0f, 5.0f)
                lineTo(9.0f, 5.0f)
                lineTo(9.0f, 3.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(21.0f, 7.0f)
                lineTo(23.0f, 7.0f)
                lineTo(23.0f, 17.0f)
                lineTo(21.0f, 17.0f)
                lineTo(21.0f, 7.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(7.0f, 5.0f)
                lineTo(9.0f, 5.0f)
                lineTo(9.0f, 7.0f)
                lineTo(7.0f, 7.0f)
                lineTo(7.0f, 5.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(11.0f, 5.0f)
                lineTo(23.0f, 5.0f)
                lineTo(23.0f, 7.0f)
                lineTo(11.0f, 7.0f)
                lineTo(11.0f, 5.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(11.0f, 9.0f)
                lineTo(23.0f, 9.0f)
                lineTo(23.0f, 19.0f)
                lineTo(11.0f, 19.0f)
                lineTo(11.0f, 9.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(5.0f, 7.0f)
                lineTo(7.0f, 7.0f)
                lineTo(7.0f, 9.0f)
                lineTo(5.0f, 9.0f)
                lineTo(5.0f, 7.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(3.0f, 9.0f)
                lineTo(5.0f, 9.0f)
                lineTo(5.0f, 11.0f)
                lineTo(3.0f, 11.0f)
                lineTo(3.0f, 9.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(1.0f, 11.0f)
                lineTo(3.0f, 11.0f)
                lineTo(3.0f, 13.0f)
                lineTo(1.0f, 13.0f)
                lineTo(1.0f, 11.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(3.0f, 13.0f)
                lineTo(5.0f, 13.0f)
                lineTo(5.0f, 15.0f)
                lineTo(3.0f, 15.0f)
                lineTo(3.0f, 13.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(5.0f, 11.0f)
                lineTo(7.0f, 11.0f)
                lineTo(7.0f, 17.0f)
                lineTo(5.0f, 17.0f)
                lineTo(5.0f, 11.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(7.0f, 9.0f)
                lineTo(9.0f, 9.0f)
                lineTo(9.0f, 19.0f)
                lineTo(7.0f, 19.0f)
                lineTo(7.0f, 9.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(9.0f, 7.0f)
                lineTo(11.0f, 7.0f)
                lineTo(11.0f, 21.0f)
                lineTo(9.0f, 21.0f)
                lineTo(9.0f, 7.0f)
                close()
            }
        }.build()
        return _back!!
    }

@Preview
@Composable
private fun BackPreview() {
    Icon(MBIcons.Pixel.Back, contentDescription = null)
}
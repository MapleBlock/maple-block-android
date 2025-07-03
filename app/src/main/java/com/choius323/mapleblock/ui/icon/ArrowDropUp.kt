package com.choius323.mapleblock.ui.icon

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private var _arrowDropUp: ImageVector? = null
val MBIcons.Pixel.ArrowDropUp: ImageVector
    get() {
        if (_arrowDropUp != null) return _arrowDropUp!!
        _arrowDropUp = ImageVector.Builder(
            name = "Icons.Pixel.ArrowDropUp",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(17.0f, 15.0f)
                lineTo(7.0f, 15.0f)
                lineTo(7.0f, 14.0f)
                lineTo(17.0f, 14.0f)
                lineTo(17.0f, 15.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(8.0f, 14.0f)
                lineTo(7.0f, 14.0f)
                lineTo(7.0f, 13.0f)
                lineTo(8.0f, 13.0f)
                lineTo(8.0f, 14.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(9.0f, 13.0f)
                lineTo(8.0f, 13.0f)
                lineTo(8.0f, 12.0f)
                lineTo(9.0f, 12.0f)
                lineTo(9.0f, 13.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(10.0f, 12.0f)
                lineTo(9.0f, 12.0f)
                lineTo(9.0f, 11.0f)
                lineTo(10.0f, 11.0f)
                lineTo(10.0f, 12.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(11.0f, 11.0f)
                lineTo(10.0f, 11.0f)
                lineTo(10.0f, 10.0f)
                lineTo(11.0f, 10.0f)
                lineTo(11.0f, 11.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(12.0f, 10.0f)
                lineTo(11.0f, 10.0f)
                lineTo(11.0f, 9.0f)
                lineTo(12.0f, 9.0f)
                lineTo(12.0f, 10.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(13.0f, 10.0f)
                lineTo(12.0f, 10.0f)
                lineTo(12.0f, 9.0f)
                lineTo(13.0f, 9.0f)
                lineTo(13.0f, 10.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(14.0f, 11.0f)
                lineTo(13.0f, 11.0f)
                lineTo(13.0f, 10.0f)
                lineTo(14.0f, 10.0f)
                lineTo(14.0f, 11.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(15.0f, 12.0f)
                lineTo(12.0f, 12.0f)
                lineTo(12.0f, 11.0f)
                lineTo(15.0f, 11.0f)
                lineTo(15.0f, 12.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(16.0f, 13.0f)
                lineTo(11.0f, 13.0f)
                lineTo(11.0f, 12.0f)
                lineTo(16.0f, 12.0f)
                lineTo(16.0f, 13.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF282A2F))) {
                moveTo(17.0f, 14.0f)
                lineTo(10.0f, 14.0f)
                lineTo(10.0f, 13.0f)
                lineTo(17.0f, 13.0f)
                lineTo(17.0f, 14.0f)
                close()
            }
        }.build()
        return _arrowDropUp!!
    }

@Preview
@Composable
private fun ArrowDropUpPreview() {
    Icon(MBIcons.Pixel.ArrowDropUp, contentDescription = null)
}
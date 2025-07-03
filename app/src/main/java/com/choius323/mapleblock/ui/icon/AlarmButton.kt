package com.choius323.mapleblock.ui.icon

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private var _alarmButton: ImageVector? = null

val MBIcons.Pixel.AlarmButton: ImageVector
    get() {
        if (_alarmButton != null) return _alarmButton!!
        _alarmButton = ImageVector.Builder(
            name = "Icons.Pixel.AlarmButton",
            defaultWidth = 24.dp, defaultHeight = 24.dp,
            viewportWidth = 24f, viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF282A2F)), stroke = null, strokeLineWidth = 0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4f,
                pathFillType = NonZero
            ) {
                moveTo(16.0f, 2.0f)
                horizontalLineTo(8.0f)
                verticalLineTo(4.0f)
                horizontalLineTo(16.0f)
                verticalLineTo(2.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF282A2F)), stroke = null, strokeLineWidth = 0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4f,
                pathFillType = NonZero
            ) {
                moveTo(20.0f, 20.0f)
                horizontalLineTo(4.0f)
                verticalLineTo(22.0f)
                horizontalLineTo(20.0f)
                verticalLineTo(20.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF282A2F)), stroke = null, strokeLineWidth = 0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4f,
                pathFillType = NonZero
            ) {
                moveTo(20.0f, 15.0f)
                horizontalLineTo(4.0f)
                verticalLineTo(17.0f)
                horizontalLineTo(20.0f)
                verticalLineTo(15.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF282A2F)), stroke = null, strokeLineWidth = 0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4f,
                pathFillType = NonZero
            ) {
                moveTo(18.0f, 4.0f)
                horizontalLineTo(16.0f)
                verticalLineTo(6.0f)
                horizontalLineTo(18.0f)
                verticalLineTo(4.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF282A2F)), stroke = null, strokeLineWidth = 0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4f,
                pathFillType = NonZero
            ) {
                moveTo(18.0f, 8.0f)
                horizontalLineTo(16.0f)
                verticalLineTo(15.0f)
                horizontalLineTo(18.0f)
                verticalLineTo(8.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF282A2F)), stroke = null, strokeLineWidth = 0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4f,
                pathFillType = NonZero
            ) {
                moveTo(16.0f, 6.0f)
                horizontalLineTo(8.0f)
                verticalLineTo(15.0f)
                horizontalLineTo(16.0f)
                verticalLineTo(6.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF282A2F)), stroke = null, strokeLineWidth = 0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4f,
                pathFillType = NonZero
            ) {
                moveTo(22.0f, 17.0f)
                horizontalLineTo(20.0f)
                verticalLineTo(20.0f)
                horizontalLineTo(22.0f)
                verticalLineTo(17.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF282A2F)), stroke = null, strokeLineWidth = 0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4f,
                pathFillType = NonZero
            ) {
                moveTo(20.0f, 19.0f)
                horizontalLineTo(6.0f)
                verticalLineTo(20.0f)
                horizontalLineTo(20.0f)
                verticalLineTo(19.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF282A2F)), stroke = null, strokeLineWidth = 0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4f,
                pathFillType = NonZero
            ) {
                moveTo(4.0f, 17.0f)
                horizontalLineTo(2.0f)
                verticalLineTo(20.0f)
                horizontalLineTo(4.0f)
                verticalLineTo(17.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF282A2F)), stroke = null, strokeLineWidth = 0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4f,
                pathFillType = NonZero
            ) {
                moveTo(8.0f, 4.0f)
                horizontalLineTo(6.0f)
                verticalLineTo(6.0f)
                horizontalLineTo(8.0f)
                verticalLineTo(4.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF282A2F)), stroke = null, strokeLineWidth = 0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4f,
                pathFillType = NonZero
            ) {
                moveTo(6.0f, 6.0f)
                horizontalLineTo(4.0f)
                verticalLineTo(16.0f)
                horizontalLineTo(6.0f)
                verticalLineTo(6.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF282A2F)), stroke = null, strokeLineWidth = 0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4f,
                pathFillType = NonZero
            ) {
                moveTo(20.0f, 6.0f)
                horizontalLineTo(18.0f)
                verticalLineTo(16.0f)
                horizontalLineTo(20.0f)
                verticalLineTo(6.0f)
                close()
            }
        }.build()
        return _alarmButton!!
    }

@Preview
@Composable
private fun AlarmButtonPreview() {
    Icon(MBIcons.Pixel.AlarmButton, null)
}
package com.choius323.mapleblock.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView

private val LightColorScheme = lightColorScheme(
    primary = MBColor.Primary,
    onPrimary = MBColor.White,
    background = MBColor.White,
    onBackground = MBColor.Gray500, // 폰트 메인
    surface = MBColor.Gray100,
    onSurface = MBColor.Gray500,    // 폰트 메인
    error = MBColor.Error,
    onError = MBColor.White,
    primaryContainer = MBColor.Primary, // WSG에 따라 primary 색상을 컨테이너로 사용
    onPrimaryContainer = MBColor.White,
    outline = MBColor.Black,
)

private val DarkColorScheme = darkColorScheme(
    primary = MBColor.Primary, //
    onPrimary = MBColor.White,
    background = Color.Black, // 다크 테마를 위한 어두운 배경
    onBackground = Color.White,
    surface = MBColor.Gray700, // 다크 테마를 위한 어두운 표면
    onSurface = MBColor.White,
    error = MBColor.Error, //
    onError = MBColor.White,
    primaryContainer = MBColor.Primary, // 다크 테마에서 컨테이너로 Primary Dark 사용
    onPrimaryContainer = MBColor.White,
    outline = MBColor.Gray200, // 다크 테마에서 외곽선을 위한 더 어두운 회색
)

@Composable
fun MBTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    // system bar (statusBar, navigationBar)
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
                window.statusBarColor = colorScheme.background.toArgb()
            } else {
//                window.statusBarColor = colorScheme.background.toArgb()
//                window.navigationBarColor = colorScheme.background.toArgb()
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // WSG에 기반하여 정의될 타이포그래피
        content = content,
        shapes = Shapes // WSG에 기반하여 정의될 모양
    )
}
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
    primary = TossBlue, //
    onPrimary = Color.White, // 흰색 텍스트 (명시되지 않았으나 일반적인 대비를 위해)
    primaryContainer = TossBlue, // WSG에 따라 primary 색상을 컨테이너로 사용
    onPrimaryContainer = Color.White,
    secondary = MapleGold, //
    onSecondary = Color.Black, // 보조 색상 위 검정 텍스트 (대비를 위해 추정)
    secondaryContainer = MapleGold, //
    onSecondaryContainer = Color.Black,
    tertiary = CosmosPurple, //
    onTertiary = Color.White,
    tertiaryContainer = CosmosPurple, //
    onTertiaryContainer = Color.White,
    background = Gray90, // 배경으로 Neutral 90% 사용
    onBackground = Color.Black, // 밝은 배경 위 검정 텍스트 (추정)
    surface = Color.White, // 카드 배경 색상
    onSurface = Color.Black,
    error = ErrorRed, // 피드백 에러 색상
    onError = Color.White,
    outline = Color.Black, // 테두리/외곽선에 Neutral 70% 사용 (입력 필드 테두리 D1D1D6 과 일치)
)

private val DarkColorScheme = darkColorScheme(
    primary = TossBlue, //
    onPrimary = Color.White,
    primaryContainer = TossBlueDark, // 다크 테마에서 컨테이너로 Primary Dark 사용
    onPrimaryContainer = Color.White,
    secondary = MapleGold, //
    onSecondary = Color.Black,
    secondaryContainer = MapleGold, //
    onSecondaryContainer = Color.Black,
    tertiary = CosmosPurple, //
    onTertiary = Color.White,
    tertiaryContainer = CosmosPurple, //
    onTertiaryContainer = Color.White,
    background = Color.Black, // 다크 테마를 위한 어두운 배경
    onBackground = Color.White,
    surface = Color(0xFF121212), // 다크 테마를 위한 어두운 표면
    onSurface = Color.White,
    error = ErrorRed, //
    onError = Color.White,
    outline = Color.Black, // 다크 테마에서 외곽선을 위한 더 어두운 회색
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
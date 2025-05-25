package com.choius323.mapleblock.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// WSG에 명시된 시스템 폰트 스택 정의
//val SystemFontFamily = FontFamily(
//    // Compose는 CSS 시스템 폰트 스택 문자열을 직접 지원하지 않습니다.
//    // 일반적으로는 폰트 대체 순서를 정의하거나 Compose의 기본 폰트 해결에 의존합니다.
//    // 여기서는 일반적인 sans-serif로 정의하고 OS의 폰트 렌더링에 의존합니다.
//)
val SystemFontFamily = FontFamily.SansSerif

val Typography = Typography(
    // Heading 1
    headlineLarge = TextStyle(
//        fontFamily = SystemFontFamily,
        fontWeight = FontWeight(700), // 700
        fontSize = 32.sp, // 32px
        lineHeight = 40.sp // 40px
    ),
    // Heading 2
    headlineMedium = TextStyle(
        fontFamily = SystemFontFamily,
        fontWeight = FontWeight(600), // 600
        fontSize = 24.sp, // 24px
        lineHeight = 32.sp // 32px
    ),
    // Heading 3
    headlineSmall = TextStyle(
        fontFamily = SystemFontFamily,
        fontWeight = FontWeight(600), // 600
        fontSize = 20.sp, // 20px
        lineHeight = 28.sp // 28px
    ),
    // Body
    bodyLarge = TextStyle( // 주요 Body 텍스트에 bodyLarge 사용
        fontFamily = SystemFontFamily,
        fontWeight = FontWeight(400), // 400
        fontSize = 16.sp, // 16px
        lineHeight = 24.sp // 24px
    ),
    // Caption
    bodySmall = TextStyle( // Caption 텍스트에 bodySmall 사용
        fontFamily = SystemFontFamily,
        fontWeight = FontWeight(400), // 400
        fontSize = 14.sp, // 14px
        lineHeight = 20.sp // 20px
    ),
    // Button Text
    labelLarge = TextStyle( // 버튼 텍스트에 labelLarge 사용
        fontFamily = SystemFontFamily,
        fontWeight = FontWeight(600), // 600
        fontSize = 16.sp, // 16px
        lineHeight = 24.sp // 24px
    )
    /*
    참고: WSG는 700, 600, 400과 같은 일반적인 폰트 굵기를 지정합니다.
    Compose의 FontWeight 클래스에는 미리 정의된 값이 있습니다 (예: FontWeight.Bold, FontWeight.SemiBold, FontWeight.Normal).
    이를 직접 매핑하면 다음과 같습니다:
    700 -> FontWeight.Bold
    600 -> FontWeight.SemiBold
    400 -> FontWeight.Normal
    */
)
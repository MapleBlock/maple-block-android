package com.choius323.mapleblock.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.choius323.mapleblock.R

// 1. 폰트 패밀리 정의
val DnfBeatFamily = FontFamily(
    Font(R.font.dnf_bit_bit, FontWeight.Normal) // '던파비트' 폰트
)

val PretendardFamily = FontFamily(
    Font(R.font.pretendard, FontWeight.Normal), // Pretendard Regular (400)
    Font(R.font.pretendard, FontWeight.Medium),  // Pretendard Medium (500)
    Font(R.font.pretendard, FontWeight.SemiBold) // Pretendard SemiBold (600)
)

// 2. MBType 객체 정의
object MBTypo {
    val Heading1 = TextStyle(
        fontFamily = DnfBeatFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 34.sp,
        lineHeight = 1.4.em,
        letterSpacing = (-0.01).em
    )
    val Heading2 = TextStyle(
        fontFamily = DnfBeatFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 1.4.em,
        letterSpacing = (-0.01).em
    )
    val Title1 = TextStyle(
        fontFamily = DnfBeatFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
        lineHeight = 1.4.em,
        letterSpacing = (-0.01).em
    )
    val Title2 = TextStyle(
        fontFamily = DnfBeatFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 1.4.em,
        letterSpacing = (-0.01).em
    )
    val Subtitle1 = TextStyle(
        fontFamily = PretendardFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 1.4.em,
        letterSpacing = (-0.01).em
    )
    val Subtitle2 = TextStyle(
        fontFamily = PretendardFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 1.4.em,
        letterSpacing = (-0.01).em
    )
    val Body1 = TextStyle(
        fontFamily = PretendardFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 1.4.em,
        letterSpacing = (-0.01).em
    )
    val Body2 = TextStyle(
        fontFamily = PretendardFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 1.4.em,
        letterSpacing = (-0.01).em
    )
    val Body3 = TextStyle(
        fontFamily = PretendardFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 1.4.em,
        letterSpacing = (-0.01).em
    )
    val Caption = TextStyle(
        fontFamily = PretendardFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 1.4.em,
        letterSpacing = (-0.01).em
    )
}

val Typography = Typography(
    headlineLarge = MBTypo.Heading1,
    headlineMedium = MBTypo.Heading2,
    titleLarge = MBTypo.Title1,
    titleMedium = MBTypo.Title2,
    titleSmall = MBTypo.Subtitle1,
    bodyLarge = MBTypo.Subtitle2,
    bodyMedium = MBTypo.Body1,
    bodySmall = MBTypo.Body3,
    labelSmall = MBTypo.Caption,
)
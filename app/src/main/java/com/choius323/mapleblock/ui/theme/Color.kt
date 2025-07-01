package com.choius323.mapleblock.ui.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


// WSG 문서에 기반한 컬러 팔레트 정의
// 기본 색상
val TossBlue = Color(0xFF007AFF) // Primary
val TossBlueDark = Color(0xFF005BB5) // Primary Dark

// 보조 색상
val MapleGold = Color(0xFFFFC538) // Secondary

// 강조 색상
val CosmosPurple = Color(0xFF8E44AD) // Accent

// 중립 색상
val Gray90 = Color(0xFFF5F5F7) // Neutral 90%
val Gray70 = Color(0xFFD1D1D6) // Neutral 70%
val Gray30 = Color(0xFF8E8E93) // Neutral 30%

// 피드백 색상
val SuccessGreen = Color(0xFF34C759) // Feedback Success
val ErrorRed = Color(0xFFFF3B30) // Feedback Error

// 새로운 컬러 시스템을 정의하는 객체
object MBColor {
    // Main (Gray Scale)
    val White = Color(0xFFFFFFFF)
    val Gray100 = Color(0xFFF3F4F6) // 배경 (white mode)
    val Gray200 = Color(0xFFE8EBF1)
    val Gray300 = Color(0xFFBFC6D4) // 폰트 비활성화
    val Gray400 = Color(0xFF888E99) // 폰트 서브
    val Gray500 = Color(0xFF282A2F) // 폰트 메인
    val Gray600 = Color(0xFF1D1F22)
    val Gray700 = Color(0xFF0E0F10) // 배경 (dark mode)
    val Black = Color(0xFF000000)

    // Primary
    val Primary400 = Color(0xFFF7C5C3)
    val Primary500 = Color(0xFFE6453F)
    val Primary = Primary500
    val Primary600 = Color(0xFFB83732)
    val Primary700 = Color(0xFF8A2926)

    // Success
    val Success400 = Color(0xFFDAEFD7)
    val Success500 = Color(0xFF56D815)
    val Success = Success500
    val Success600 = Color(0xFF4DC213)
    val Success700 = Color(0xFF45AD11)

    // Error
    val Error = Color(0xFFF4183D)

    // Sub (Other Platform)
    val Discord = Color(0xFF5865F8)
    val X = Color(0xFF000000)
    val Telegram = Color(0xFF28A8EA)
    val Website = Color(0xFFFF7AF9)
}
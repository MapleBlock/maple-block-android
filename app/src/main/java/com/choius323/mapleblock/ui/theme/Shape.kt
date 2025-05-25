package com.choius323.mapleblock.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// WSG에 기반한 UI 컴포넌트의 모양 정의
val Shapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp), // 명시적으로 정의되지 않았지만, 작은 요소에 권장되는 방법
    small = RoundedCornerShape(6.dp), // 버튼, 입력 필드에 사용 
    medium = RoundedCornerShape(8.dp), // 카드에 사용 
    large = RoundedCornerShape(12.dp),
    extraLarge = RoundedCornerShape(16.dp)
)
package com.choius323.mapleblock.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.choius323.mapleblock.ui.theme.Gray90
import com.choius323.mapleblock.ui.theme.MBTheme
import com.choius323.mapleblock.ui.theme.TossBlue

// 5.1 버튼(Button) 컴포넌트 [cite: 10]
@Composable
fun MBPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .heightIn(min = 48.dp), // 최소 높이를 위한 임의 설정 (패딩 12px * 2 + 폰트 16px = 40px, 여기에 여유 줌)
        enabled = enabled,
        shape = MaterialTheme.shapes.small, // border-radius: 6px [cite: 11] -> MaterialTheme.shapes.small (6.dp)
        colors = ButtonDefaults.buttonColors(
            containerColor = TossBlue, // background-color: #007AFF [cite: 11]
            contentColor = Color.White, // color: #FFFFFF [cite: 11]
            disabledContainerColor = TossBlue.copy(alpha = 0.5f), // Disabled: opacity 50% [cite: 13]
            disabledContentColor = Color.White.copy(alpha = 0.5f)
        ),
        contentPadding = PaddingValues(
            horizontal = 24.dp,
            vertical = 12.dp
        ) // padding: 12px 24px [cite: 11]
    ) {
        MBText(
            text = text,
            style = MaterialTheme.typography.labelLarge // font-size: 16px, font-weight: 600 [cite: 11]
        )
    }
}

@Composable
fun MBSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OutlinedButton( // OutlinedButton을 사용하여 테두리 스타일 적용
        onClick = onClick,
        modifier = modifier
            .heightIn(min = 48.dp), // 최소 높이를 위한 임의 설정
        enabled = enabled,
        shape = MaterialTheme.shapes.small, // border-radius: 6px [cite: 12] -> MaterialTheme.shapes.small (6.dp)
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Gray90, // background-color: #F5F5F7 [cite: 12]
            contentColor = TossBlue, // color: #007AFF [cite: 12]
            disabledContainerColor = Gray90.copy(alpha = 0.5f), // Disabled: opacity 50% [cite: 13]
            disabledContentColor = TossBlue.copy(alpha = 0.5f)
        ),
        border = ButtonDefaults.outlinedButtonBorder(true).copy(
            // border: 1px solid #007AFF [cite: 12]
            brush = SolidColor(TossBlue),
            width = 1.dp,
        ),
        contentPadding = PaddingValues(
            horizontal = 24.dp,
            vertical = 12.dp
        ) // padding: 12px 24px [cite: 12]
    ) {
        MBText(
            text = text,
            style = MaterialTheme.typography.labelLarge // font-size: 16px, font-weight: 600 [cite: 12]
        )
    }
}


// 프리뷰
@Preview(showBackground = true)
@Composable
fun MapleButtonPreview() {
    MBTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            MBPrimaryButton(text = "기본 버튼", onClick = { /* TODO */ })
            MBPrimaryButton(text = "비활성화 버튼", onClick = { /* TODO */ }, enabled = false)
            MBSecondaryButton(text = "보조 버튼", onClick = { /* TODO */ })
            MBSecondaryButton(text = "보조 비활성화 버튼", onClick = { /* TODO */ }, enabled = false)
        }
    }
}
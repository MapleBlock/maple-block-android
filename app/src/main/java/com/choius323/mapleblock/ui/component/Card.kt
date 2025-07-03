package com.choius323.mapleblock.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.choius323.mapleblock.ui.theme.MBTheme


// 5.3 카드(Card) 컴포넌트 [cite: 16]
@Composable
fun MBCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .shadow(
                elevation = 2.dp,
                shape = MaterialTheme.shapes.medium,
                ambientColor = Color.Black.copy(alpha = 0.05f),
                spotColor = Color.Black.copy(alpha = 0.05f)
            ),
        shape = MaterialTheme.shapes.medium,
    ) {
        Box(modifier = Modifier.padding(16.dp)) { // Padding: 16px [cite: 16]
            content()
        }
    }
}

@Preview
@Composable
private fun MapleCardPreview() {
    MBTheme {
        Surface {
            MBCard(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    MBText(
                        text = "이것은 카드 컴포넌트입니다.",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    MBText(
                        text = "카드 내부의 콘텐츠는 여기에 표시됩니다.",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
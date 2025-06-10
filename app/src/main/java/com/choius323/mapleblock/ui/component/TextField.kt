package com.choius323.mapleblock.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.choius323.mapleblock.ui.theme.Gray30
import com.choius323.mapleblock.ui.theme.MBTheme


// 5.2 입력 필드(Input) 컴포넌트 [cite: 14]
@Composable
fun MBTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "",
    singleLine: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline, // border: 1px solid #D1D1D6 [cite: 14] -> MaterialTheme.colorScheme.outline
                shape = MaterialTheme.shapes.small // border-radius: 6px [cite: 14] -> MaterialTheme.shapes.small (6.dp)
            )
            .padding(12.dp) // padding: 12px [cite: 14]
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge,
            singleLine = singleLine,
            keyboardOptions = keyboardOptions,
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        hint,
                        color = Gray30,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                innerTextField()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MapleTextFieldPreview() {
    MBTheme {
        Column {
            MBTextField(
                value = "",
                onValueChange = { },
                hint = "입력해주세요",
                modifier = Modifier.padding(16.dp)
            )
            HorizontalDivider()
            MBTextField(
                value = "입력된 값 입니다.",
                onValueChange = { },
                hint = "입력해주세요",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

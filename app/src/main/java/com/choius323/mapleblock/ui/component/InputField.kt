package com.choius323.mapleblock.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.choius323.mapleblock.ui.theme.MBTheme


// 5.2 입력 필드(Input) 컴포넌트 [cite: 14]
@Composable
fun MapleTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: @Composable (() -> Unit)? = null,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline, // border: 1px solid #D1D1D6 [cite: 14] -> MaterialTheme.colorScheme.outline
                shape = MaterialTheme.shapes.small // border-radius: 6px [cite: 14] -> MaterialTheme.shapes.small (6.dp)
            )
            .padding(12.dp), // padding: 12px [cite: 14]
        textStyle = MaterialTheme.typography.bodyLarge.copy( // font-size: 16px [cite: 14]
            color = MaterialTheme.colorScheme.onSurface
        ),
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        decorationBox = { innerTextField ->
            if (value.isEmpty() && placeholder != null) {
                placeholder()
            }
            innerTextField()
        }
    )
}

@Preview
@Composable
private fun MapleTextFieldPreview() {
    var textValue by remember { mutableStateOf("") }
    MBTheme {
        MapleTextField(
            value = textValue,
            onValueChange = { textValue = it },
            placeholder = { Text("입력해주세요") },
            modifier = Modifier.padding(16.dp)
        )
    }
}
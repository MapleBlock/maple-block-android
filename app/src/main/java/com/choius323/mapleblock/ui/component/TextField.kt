package com.choius323.mapleblock.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.choius323.mapleblock.ui.theme.Gray30
import com.choius323.mapleblock.ui.theme.MBTheme

@Composable
fun MBTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "",
    singleLine: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RectangleShape,
            )
            .padding(16.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        textStyle = MaterialTheme.typography.bodyLarge,
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        decorationBox = { innerTextField ->
            if (value.isEmpty()) {
                MBText(
                    hint,
                    color = Gray30,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.height(IntrinsicSize.Max)
                )
            }
            innerTextField()
        }
    )
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

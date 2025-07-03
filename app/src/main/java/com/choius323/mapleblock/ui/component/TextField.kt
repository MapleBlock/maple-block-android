package com.choius323.mapleblock.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.choius323.mapleblock.ui.theme.MBColor
import com.choius323.mapleblock.ui.theme.MBTheme
import com.choius323.mapleblock.ui.theme.MBTypo

@Composable
fun MBTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "",
    singleLine: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isError: Boolean = false,
) {
    val colorScheme = MaterialTheme.colorScheme
    val color by remember(isError, colorScheme) {
        derivedStateOf { if (isError) colorScheme.error else colorScheme.onSurface }
    }
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .border(
                width = 2.dp,
                color = color,
                shape = RectangleShape,
            )
            .padding(16.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        textStyle = MBTypo.Body2.copy(color = color),
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        decorationBox = { innerTextField ->
            if (value.isEmpty()) {
                MBText(
                    text = hint,
                    color = MBColor.Gray400,
                    style = MBTypo.Body2,
                    modifier = Modifier.height(IntrinsicSize.Max)
                )
            }
            innerTextField()
        }
    )
}

@Composable
fun MBTextFieldSection(
    sectionType: String,
    inputText: String,
    modifier: Modifier = Modifier,
    hint: String = "${sectionType}을 입력해주세요.",
    isEssential: Boolean = false,
    information: String = "",
    singleLine: Boolean = false,
    textFieldModifier: Modifier = Modifier,
    isError: Boolean = false,
    errorMessage: String = "",
    onTextChange: (String) -> Unit,
) {
    val colorScheme = MaterialTheme.colorScheme
    Column(modifier.padding(vertical = 16.dp)) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                MBText(text = sectionType)
                if (isEssential) {
                    Box(
                        Modifier
                            .align(Alignment.Top)
                            .padding(top = 3.dp, start = 2.67.dp)
                            .size(4.dp)
                            .rotate(45f)
                            .background(MaterialTheme.colorScheme.error)
                    )
                }
                MBText(
                    text = information,
                    color = MBColor.Gray400,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End
                )
            }
        }
        Spacer(Modifier.height(8.dp))
        MBTextField(
            value = inputText,
            onValueChange = { onTextChange(it) },
            hint = hint,
            singleLine = singleLine,
            modifier = textFieldModifier,
            isError = isError,
        )
        Spacer(Modifier.height(8.dp))
        if (isError) {
            MBText(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MBTypo.Body1,
            )
        }
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
            MBHorizonDivider()
            MBTextField(
                value = "입력된 값 입니다.",
                onValueChange = { },
                hint = "입력해주세요",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MBTextFieldSectionPreview() {
    MBTheme {
        Surface {
            Column {
                MBTextFieldSection(
                    sectionType = "제목",
                    inputText = "",
                    isEssential = false,
                    modifier = Modifier.padding(16.dp),
                    information = "",
                    singleLine = true,
                    onTextChange = {}
                )
                MBHorizonDivider()
                MBTextFieldSection(
                    sectionType = "본문",
                    inputText = "입력된 내용입니다. ".repeat(10),
                    isEssential = true,
                    modifier = Modifier.padding(16.dp),
                    information = "83/1035",
                    singleLine = false,
                    onTextChange = {}
                )
                MBHorizonDivider()
                MBTextFieldSection(
                    sectionType = "본문",
                    inputText = "입력된 내용입니다. ".repeat(10),
                    isEssential = true,
                    modifier = Modifier.padding(16.dp),
                    information = "83/1035",
                    singleLine = false,
                    onTextChange = {},
                    isError = true,
                    errorMessage = "오류 메시지입니다."
                )
            }
        }
    }
}
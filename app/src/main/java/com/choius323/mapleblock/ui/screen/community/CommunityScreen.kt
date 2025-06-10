package com.choius323.mapleblock.ui.screen.community

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.choius323.mapleblock.ui.component.MBPrimaryButton

@Composable
fun CommunityScreen(
    modifier: Modifier = Modifier,
    goWritePost: () -> Unit,
) {
    Column(modifier) {
        Text("Community Screen")
        MBPrimaryButton(
            text = "Write Post",
            onClick = goWritePost,
        )
    }
}
package com.choius323.mapleblock.ui.screen.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun SettingScreen(
    modifier: Modifier = Modifier
) {
    var num by rememberSaveable { mutableIntStateOf(0) }
    Column {
        Text("num: $num", modifier = modifier.clickable { num++ })
        Text("Setting Screen")
    }
}
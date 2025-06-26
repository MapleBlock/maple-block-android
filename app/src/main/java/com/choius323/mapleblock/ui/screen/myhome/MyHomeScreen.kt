package com.choius323.mapleblock.ui.screen.myhome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.choius323.mapleblock.R
import com.choius323.mapleblock.ui.component.MBText
import com.choius323.mapleblock.ui.component.ProvideAppBar
import com.choius323.mapleblock.ui.theme.ErrorRed
import com.choius323.mapleblock.ui.theme.Gray30
import com.choius323.mapleblock.ui.theme.Gray90
import com.choius323.mapleblock.ui.theme.MBTheme

@Composable
fun MyHomeScreen(
    modifier: Modifier = Modifier,
    goProfileScreen: () -> Unit,
) {
    ProvideAppBar(
        showAppBar = true,
        navigationIcon = { MBText("마이홈", fontSize = 28.sp) },
    )
    MyHomeScreenContent(
        modifier = modifier,
        clickProfile = goProfileScreen
    )
}

@Composable
fun MyHomeScreenContent(
    modifier: Modifier = Modifier,
    clickProfile: () -> Unit = {},
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
    ) {
        ProfileSection(Modifier.clickable(onClick = clickProfile))
        Spacer(Modifier.height(16.dp))
        HorizontalDivider(
            thickness = 16.dp,
            color = Gray90,
        )
        Spacer(Modifier.height(8.dp))
        MenuItems()
    }
}

@Composable
fun ProfileSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(144.dp)
                .clip(RectangleShape)
                .background(Color.LightGray)
        )
        Spacer(Modifier.height(8.dp))
        MBText(
            text = "오노고오",
            fontSize = 28.sp,
        )
        MBText(
            text = "maple123@naver.com",
            color = Gray30,
            fontSize = 14.sp,
        )
    }
}

@Composable
fun MenuItems(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        MenuItem(text = "차단게시물/유저관리")
        MenuDivider()
        MenuItem(text = "모드 변경", value = "현재 : 화이트")
        MenuDivider()
        MenuItem(text = "개인정보처리방침/이용약관")
        MenuDivider()
        MenuItem(
            text = "로그아웃/계정탈퇴",
            textColor = ErrorRed,
            iconColor = ErrorRed
        )
        MenuDivider()
        MenuItem(text = "앱 버전", value = "v1.0.0", showArrow = false)
    }
}

@Composable
fun MenuDivider(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = modifier.padding(vertical = 8.dp),
        thickness = 2.dp,
        color = Gray90,
    )
}

@Composable
fun MenuItem(
    text: String,
    modifier: Modifier = Modifier,
    value: String? = null,
    textColor: Color = Color.Unspecified,
    iconColor: Color = MaterialTheme.colorScheme.onSurface,
    showArrow: Boolean = true,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {}
            .padding(vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MBText(text = text, fontSize = 16.sp, color = textColor)
        Spacer(modifier = Modifier.weight(1f))
        if (value != null) {
            MBText(text = value, fontSize = 16.sp, color = Gray30)
            Spacer(modifier = Modifier.width(8.dp))
        }
        if (showArrow) {
            val icon = if (text.contains("로그아웃"))
                Icons.AutoMirrored.Filled.ExitToApp
            else
                Icons.AutoMirrored.Rounded.ArrowForwardIos

            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = iconColor,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyHomeScreenPreview() {
    MBTheme {
        Surface {
            MyHomeScreenContent()
        }
    }
}
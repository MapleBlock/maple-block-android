package com.choius323.mapleblock.ui.screen.myhome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.choius323.mapleblock.R
import com.choius323.mapleblock.ui.component.MBHorizonDivider
import com.choius323.mapleblock.ui.component.MBText
import com.choius323.mapleblock.ui.component.ProvideAppBar
import com.choius323.mapleblock.ui.icon.Edit
import com.choius323.mapleblock.ui.icon.Forward
import com.choius323.mapleblock.ui.icon.MBIcons
import com.choius323.mapleblock.ui.theme.Gray90
import com.choius323.mapleblock.ui.theme.MBColor
import com.choius323.mapleblock.ui.theme.MBTheme
import com.choius323.mapleblock.ui.theme.MBTypo

@Composable
fun MyHomeScreen(
    modifier: Modifier = Modifier,
    goProfileScreen: () -> Unit,
) {
    ProvideAppBar(
        showAppBar = true,
        navigationIcon = { MBText("마이홈", style = MBTypo.Title2) },
    )
    MyHomeScreenContent(
        modifier = modifier,
    )
}

@Composable
fun MyHomeScreenContent(
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        ProfileSection(Modifier)
        Spacer(Modifier.height(16.dp))
        HorizontalDivider(
            thickness = 16.dp,
            color = Gray90,
        )
        Spacer(Modifier.height(8.dp))
        MenuItems()
        Spacer(Modifier.weight(1f))
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 13.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MBText(text = "로그아웃", style = MBTypo.Body1, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.weight(1f))
            MBText(
                text = "계정탈퇴", color = MaterialTheme.colorScheme.error, style = MBTypo.Body1,
            )
        }
    }
}

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(64.dp)
                    .clip(RectangleShape)
                    .background(Color.LightGray)
            )
            Spacer(Modifier.width(16.dp))
            Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                MBText(
                    text = "오노고오",
                    style = MBTypo.Subtitle1,
                )
                MBText(
                    text = "maple123@naver.com",
                    color = MBColor.Gray400,
                    style = MBTypo.Body2,
                )
            }
        }
        Icon(
            MBIcons.Pixel.Edit,
            contentDescription = "프로필 편집",
            modifier = Modifier.clickable(onClick = {})
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
        MenuItem(text = "모드 변경")
        MenuDivider()
        MenuItem(text = "개인정보처리방침/이용약관")
        MenuDivider()
        MenuItem(text = "앱 버전")
    }
}

@Composable
private fun MenuDivider(modifier: Modifier = Modifier) {
    MBHorizonDivider(
        modifier = modifier.padding(vertical = 8.dp),
    )
}

@Composable
fun MenuItem(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = Color.Unspecified,
    iconColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {}
            .padding(vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MBText(text = text, style = MBTypo.Body1, color = textColor)
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = MBIcons.Pixel.Forward,
            contentDescription = text,
            tint = iconColor,
            modifier = Modifier.size(18.dp)
        )
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
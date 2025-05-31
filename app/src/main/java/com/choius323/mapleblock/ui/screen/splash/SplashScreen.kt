package com.choius323.mapleblock.ui.screen.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.choius323.mapleblock.R
import com.choius323.mapleblock.ui.theme.Gray30
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreen() {
                finish()
            }
        }
    }
}

@Preview
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    closeScreen: () -> Unit = {}
) {
    val alpha = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = Unit) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(1500)
        )
        delay(2000L)
        closeScreen()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Gray30),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val painter = painterResource(R.mipmap.ic_launcher_foreground)

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .size(200.dp)
                    .alpha(alpha.value),
                painter = painter,
                contentDescription = "app logo",
            )
        }

//            Image(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .alpha(alpha.value),
//                painter = painterResource(id = R.drawable.splash_screen_bottom),
//                contentScale = ContentScale.FillWidth,
//                contentDescription = "Welluga Staff"
//            )
    }
}
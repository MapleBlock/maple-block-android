package com.choius323.mapleblock.ui.screen.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.choius323.mapleblock.R
import com.choius323.mapleblock.ui.theme.MBTheme
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MBTheme(false) {
                Surface {
                    SplashScreen(
                        closeScreen = { finish() }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    closeScreen: () -> Unit = {},
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
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(200.dp)
                .alpha(alpha.value),
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "app logo",
            contentScale = ContentScale.FillBounds
        )
        Image(
            painter = painterResource(R.drawable.ic_title),
            contentDescription = "app title",
            modifier = Modifier.size(width = 280.dp, height = 100.dp),
            contentScale = ContentScale.Crop
        )
    }
}
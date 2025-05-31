package com.choius323.mapleblock.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import com.choius323.mapleblock.ui.navigation.MainNavController
import com.choius323.mapleblock.ui.screen.splash.SplashActivity
import com.choius323.mapleblock.ui.theme.MBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        val intent = Intent(this, SplashActivity::class.java)
        (this as? Activity)?.startActivity(intent)
        setContent {
            MBTheme(false) {
                MainNavController(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding()
                )
            }
        }
    }
}
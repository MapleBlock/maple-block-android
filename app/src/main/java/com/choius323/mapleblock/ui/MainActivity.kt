package com.choius323.mapleblock.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.choius323.mapleblock.ui.component.MBTopAppBar
import com.choius323.mapleblock.ui.navigation.BottomNavigationBar
import com.choius323.mapleblock.ui.navigation.MainNavController
import com.choius323.mapleblock.ui.navigation.rememberMBNavController
import com.choius323.mapleblock.ui.screen.splash.SplashActivity
import com.choius323.mapleblock.ui.theme.MBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        if (savedInstanceState == null) {
            val intent = Intent(this, SplashActivity::class.java)
            (this as? Activity)?.startActivity(intent)
        }
        setContent {
            MBTheme(false) {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberMBNavController<MainNavController>()
    Scaffold(
        topBar = {
            MBTopAppBar(
                mainNavController = navController,
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
            )
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                modifier = Modifier.fillMaxWidth()
            )
        },
        modifier = Modifier
            .navigationBarsPadding()
            .background(MaterialTheme.colorScheme.background)
    ) { innerPadding ->
        MainNavController(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
        )
    }
}
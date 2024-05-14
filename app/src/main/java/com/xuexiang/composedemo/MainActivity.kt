package com.xuexiang.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.xuexiang.composedemo.navi.AppRoot
import com.xuexiang.composedemo.ui.theme.ComposeDemoTheme
import com.xuexiang.composedemo.ui.widget.CardStyle
import com.xuexiang.composedemo.ui.widget.Config
import com.xuexiang.composedemo.ui.widget.LocalCardStyle
import com.xuexiang.composedemo.ui.widget.LocalConfig

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val cardStyle = if (isSystemInDarkTheme()) {
                CardStyle(containerColor = Color.White, textColor = Color.Black, elevation = 8.dp)
            } else {
                CardStyle(
                    containerColor = Color.DarkGray,
                    textColor = Color.White,
                    elevation = 16.dp
                )
            }
            val config = Config(isDebug = true, baseUrl = "https://192.168.0.1")
            CompositionLocalProvider(
                LocalCardStyle provides cardStyle, LocalConfig provides config
            ) {
                ComposeDemoTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        AppRoot()
                    }
                }
            }
        }
    }
}
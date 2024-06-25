package com.xuexiang.composedemo.ui.widget

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

// 这个一般都在根结点(顶层)设置
data class CardStyle(
    val containerColor: Color = Color.White,
    val textColor: Color = Color.Black,
    val elevation: Dp = 8.dp
)

// Define a CompositionLocal global object with a default
// This instance can be accessed by all composables in the app
val LocalCardStyle = compositionLocalOf { CardStyle() }

// 不常变化的可以使用staticCompositionLocalOf
data class Config(val isDebug: Boolean = false, val baseUrl: String = "")

val LocalConfig = staticCompositionLocalOf { Config() }

val LocalNavController = staticCompositionLocalOf<NavHostController> { error("LocalNavController Not provided") }

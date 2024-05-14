package com.xuexiang.composedemo.ui.page.basic

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.xuexiang.composedemo.ui.widget.ScrollColumnArea

/**
 * 默认情况下，所有参数都会被解析为字符串
 */
@Composable
fun DefaultArgumentScreen(argument: String) {
    ScrollColumnArea {
        Text(text = "页面参数: $argument")
    }
}

@Composable
fun TypeArgumentScreen(argument: Int) {
    ScrollColumnArea {
        Text(text = "页面参数+5 = ${argument + 5}")
    }
}
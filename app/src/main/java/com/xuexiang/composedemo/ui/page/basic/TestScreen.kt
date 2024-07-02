package com.xuexiang.composedemo.ui.page.basic

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.xuexiang.composedemo.ui.widget.ScrollColumnArea
import java.io.Serializable

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
fun TypeArgumentScreen(argument1: String, argument2: Int) {
    ScrollColumnArea {
        Text(text = "默认类型参数 = $argument1, 指定类型参数 = $argument2")
    }
}

@Composable
fun OptionalArgumentScreen(argument: Int, argument2: String, user: User?) {
    ScrollColumnArea {
        Text(text = "页面参数1 = $argument, 页面参数2 = $argument2, user = $user")
    }
}

data class User(
    val name: String,
    val age: Int
) : Serializable
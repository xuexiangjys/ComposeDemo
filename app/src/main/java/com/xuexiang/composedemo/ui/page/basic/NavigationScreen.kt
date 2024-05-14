package com.xuexiang.composedemo.ui.page.basic

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.xuexiang.composedemo.ui.widget.ScrollColumnArea

/**
 * https://developer.android.com/develop/ui/compose/navigation?hl=zh-cn
 */
@Composable
fun NavigationScreen(navController: NavController) {
    ScrollColumnArea {

        Button(onClick = {
            navController.navigate("test/default_argument/这个是我传入的参数")
        }) {
            Text("默认类型参数页面路由")
        }

        Button(onClick = {
            navController.navigate("test/type_argument/1234")
        }) {
            Text("指定类型参数页面")
        }

    }
}
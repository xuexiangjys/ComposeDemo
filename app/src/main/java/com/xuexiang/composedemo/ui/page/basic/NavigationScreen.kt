package com.xuexiang.composedemo.ui.page.basic

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.xuexiang.composedemo.navi.ARGUMENT_KEY
import com.xuexiang.composedemo.ui.widget.ScrollColumnArea
import com.xuexiang.composedemo.ui.widget.argumentOptionalValue

/**
 * https://developer.android.com/develop/ui/compose/navigation?hl=zh-cn
 *
 * 可选参数: Destination+?+{名称=参数值的key}&{名称=参数值的key}
 * 注意参数与参数间一定要以“&”进行分隔
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

        Button(onClick = {
            navController.navigate(
                "test/optional_argument?${ARGUMENT_KEY.argumentOptionalValue(4321)}"
            )
        }) {
            Text("可选参数页面")
        }
    }
}
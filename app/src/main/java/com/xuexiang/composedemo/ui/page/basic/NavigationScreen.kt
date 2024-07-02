package com.xuexiang.composedemo.ui.page.basic

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.xuexiang.composedemo.TestActivity
import com.xuexiang.composedemo.navi.ARGUMENT_KEY
import com.xuexiang.composedemo.navi.ARGUMENT_KEY2
import com.xuexiang.composedemo.navi.ARGUMENT_KEY3
import com.xuexiang.composedemo.ui.widget.ScrollColumnArea
import com.xuexiang.composedemo.ui.widget.putOptionalArguments
import kotlinx.coroutines.launch

/**
 * https://developer.android.com/develop/ui/compose/navigation?hl=zh-cn
 *
 * 必选参数: Destination/{参数值的key}/{参数值的key}
 * 可选参数: Destination+?+{名称=参数值的key}&{名称=参数值的key}
 * 注意参数与参数间一定要以“&”进行分隔
 *
 * 必选参数只支持基础类型的参数.
 * 非基础类型参数,如Serializable等对象,只支持可选参数.
 */
@Composable
fun NavigationScreen(navController: NavController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var resultText by remember { mutableStateOf("") }
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data1 = result.data?.getIntExtra(ARGUMENT_KEY, 0)
                val data2 = result.data?.getStringExtra(ARGUMENT_KEY2)
                resultText = "返回的数据: 数据1=${data1}, 数据2=$data2"
            }
        }

    ScrollColumnArea {
        Button(onClick = {
            navController.navigate("test/default_argument/这个是我传入的参数")
        }) {
            Text("(必选参数)默认类型参数页面")
        }

        Button(onClick = {
//            navController.navigate(
//                "test/type_argument" + putArguments(
//                    listOf(
//                        "默认参数是String类型", 54321
//                    )
//                )
//            )
            navController.navigate(
                "test/type_argument/默认参数是String类型/54321"
            )
        }) {
            Text("(必选参数)指定类型参数页面")
        }

        Button(onClick = {
            navController.navigate(
                "test/optional_argument" + putOptionalArguments(
                    mapOf(
                        ARGUMENT_KEY to 4321,
                        ARGUMENT_KEY3 to User("张三", 25)
                    )
                )
            )
        }) {
            Text("(可选参数)多种参数传递页面")
        }

        Button(onClick = {
            coroutineScope.launch {
                launcher.launch(Intent(context, TestActivity::class.java).apply {
                    putExtra(ARGUMENT_KEY, 1234)
                    putExtra(ARGUMENT_KEY2, "这是从compose传过来的参数")
                })
            }
        }) {
            Text("跳转到Activity")
        }

        Text(text = resultText)
    }
}
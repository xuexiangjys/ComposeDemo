package com.xuexiang.composedemo.ui.page.basic

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.xuexiang.composedemo.navi.BasicPages
import com.xuexiang.composedemo.ui.widget.ScreenTemplate

@Composable
fun BasicScreen(navController: NavController) {
    ScreenTemplate(navController, BasicPages)
}
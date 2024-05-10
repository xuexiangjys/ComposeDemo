package com.xuexiang.composedemo.ui.page.component

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.xuexiang.composedemo.navi.ComponentPages
import com.xuexiang.composedemo.ui.widget.ScreenTemplate

@Composable
fun ComponentScreen(navController: NavController) {
    ScreenTemplate(navController, ComponentPages)
}


package com.xuexiang.composedemo.ui.page.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.xuexiang.composedemo.navi.HomePages
import com.xuexiang.composedemo.ui.widget.ScreenTemplate

@Composable
fun HomeScreen(navController: NavController) {
    ScreenTemplate(navController, HomePages)
}



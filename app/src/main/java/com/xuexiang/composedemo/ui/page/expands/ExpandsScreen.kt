package com.xuexiang.composedemo.ui.page.expands

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.xuexiang.composedemo.navi.ExpandsPages
import com.xuexiang.composedemo.ui.widget.ScreenTemplate

@Composable
fun ExpandsScreen(navController: NavController) {
    ScreenTemplate(navController, ExpandsPages)
}

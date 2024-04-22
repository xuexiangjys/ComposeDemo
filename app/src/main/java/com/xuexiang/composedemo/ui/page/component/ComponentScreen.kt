package com.xuexiang.composedemo.ui.page.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.xuexiang.composedemo.navi.ComponentPages
import com.xuexiang.composedemo.navi.navigationTo
import com.xuexiang.composedemo.ui.widget.CardItem

@Composable
fun ComponentScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp), contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn {
            items(ComponentPages.size) {
                CardItem(ComponentPages[it].title) {
                    navController.navigationTo(ComponentPages[it])
                }
            }
        }
    }
}

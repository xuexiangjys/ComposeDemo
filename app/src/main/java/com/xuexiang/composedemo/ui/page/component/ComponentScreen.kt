package com.xuexiang.composedemo.ui.page.component

import androidx.compose.foundation.layout.Arrangement
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
import com.xuexiang.composedemo.ui.widget.DemoScreenItem

@Composable
fun ComponentScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp), contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(ComponentPages.size) {
                DemoScreenItem(navController, ComponentPages[it])
            }
        }
    }
}


package com.xuexiang.composedemo.ui.page.expands

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NestedScrollScreen() {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        DemoArea(
            modifier = Modifier
                .height(50.dp)
                .background(color = Color.Yellow), "顶部区域"
        )
    }, floatingActionButton = {
        FloatingActionButton(onClick = { }) {
            Icon(
                Icons.Filled.Add, contentDescription = "Add Button"
            )
        }
    }, bottomBar = {
        DemoArea(
            modifier = Modifier
                .height(80.dp)
                .background(color = Color.Green), "底部区域"
        )
    }) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
//                .nestedScroll(scrollBehavior.nestedScrollConnection)
        ) {
            items(30) {
                Text(
                    text = "列表内容:$it", modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun DemoArea(modifier: Modifier = Modifier, text: String) {
    Box(
        modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center
    ) {
        Text(text = text)
    }
}
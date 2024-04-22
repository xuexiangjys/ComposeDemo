package com.xuexiang.composedemo.ui.page.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import coil.compose.AsyncImage

@Composable
fun ImageLoadScreen() {
    Column {
        AsyncImage(
            model = "https://cdn.pixabay.com/photo/2024/03/07/10/38/simba-8618301_1280.jpg",
            contentDescription = null,
        )
    }
}
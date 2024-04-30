package com.xuexiang.composedemo.ui.page.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xuexiang.composedemo.ui.widget.ScrollColumnArea
import com.xuexiang.composedemo.ui.widget.Toast

@Composable
fun ButtonScreen() {
    var toastMessage by remember { mutableStateOf("") }
    var showToast by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        if (showToast) {
            Toast(modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp),
                message = toastMessage,
                onDismiss = {
                    showToast = false
                })
        }

        ScrollColumnArea {
            Button(onClick = {
                toastMessage = "点击了实心按钮"
                showToast = true
            }) {
                Text("实心按钮")
            }

            FilledTonalButton(onClick = {
                toastMessage = "点击了填充色调按钮"
                showToast = true
            }) {
                Text("填充色调按钮")
            }

            OutlinedButton(onClick = {
                toastMessage = "点击了轮廓按钮"
                showToast = true
            }) {
                Text("轮廓按钮")
            }

            ElevatedButton(onClick = {
                toastMessage = "点击了凸起按钮"
                showToast = true
            }) {
                Text("凸起按钮")
            }

            TextButton(onClick = {
                toastMessage = "点击了文本按钮"
                showToast = true
            }) {
                Text("文本按钮")
            }
        }
    }

}
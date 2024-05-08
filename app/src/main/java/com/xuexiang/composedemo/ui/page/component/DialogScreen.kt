package com.xuexiang.composedemo.ui.page.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.xuexiang.composedemo.ui.widget.ScrollColumnArea

@Composable
fun DialogScreen() {
    var showAlertDialog by remember { mutableStateOf(false) }
    var showLoadingDialog by remember { mutableStateOf(false) }

    ScrollColumnArea {
        when {
            showAlertDialog -> {
                SimpleAlertDialog(
                    onDismissRequest = { showAlertDialog = false },
                    onConfirmation = {
                        showAlertDialog = false
                        // Add logic here to handle confirmation.
                    },
                    dialogTitle = "对话框AlertDialog演示",
                    dialogText = "这是对话框的内容...这是对话框的内容..这是对话框的内容..",
                    icon = Icons.Default.Info
                )
            }

            showLoadingDialog -> {
                LoadingDialog {
                    showLoadingDialog = false
                }
            }
        }

        Button(onClick = {
            showAlertDialog = true
        }) {
            Text("简单对话框:AlertDialog演示")
        }

        Button(onClick = {
            showLoadingDialog = true
        }) {
            Text("自定义对话框:Dialog演示")
        }
    }
}


@Composable
fun SimpleAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(icon = {
        Icon(icon, contentDescription = "Example Icon")
    }, title = {
        Text(text = dialogTitle)
    }, text = {
        Text(text = dialogText)
    }, onDismissRequest = {
        onDismissRequest()
    }, confirmButton = {
        TextButton(onClick = {
            onConfirmation()
        }) {
            Text("确认")
        }
    }, dismissButton = {
        TextButton(onClick = {
            onDismissRequest()
        }) {
            Text("取消")
        }
    })
}

@Composable
fun LoadingDialog(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier.wrapContentSize(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier.padding(vertical = 32.dp, horizontal = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(48.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
                Text(
                    text = "加载中...",
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}
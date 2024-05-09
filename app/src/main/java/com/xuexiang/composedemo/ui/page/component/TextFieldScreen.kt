package com.xuexiang.composedemo.ui.page.component

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.xuexiang.composedemo.R
import com.xuexiang.composedemo.ui.widget.ScrollColumnArea
import com.xuexiang.composedemo.ui.widget.showToast

@Composable
fun TextFieldScreen() {
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    ScrollColumnArea {
        TextField(modifier = Modifier.fillMaxWidth(), value = userName, onValueChange = {
            userName = it
        }, prefix = {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = "",
                modifier = Modifier.padding(end = 8.dp)
            )
        }, suffix = {
            if (userName.isNotEmpty()) {
                Icon(imageVector = Icons.Outlined.Clear,
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        userName = ""
                    })
            }
        }, placeholder = {
            Text(text = "请输入用户名...")
        })

        var showPassword by remember { mutableStateOf(false) }
        OutlinedTextField(modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = {
                password = it
            },
            prefix = {
                Icon(
                    imageVector = Icons.Outlined.Lock,
                    contentDescription = "",
                    modifier = Modifier.padding(end = 8.dp)
                )
            },
            suffix = {
                if (password.isNotEmpty()) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(painter = painterResource(id = if (showPassword) R.drawable.password_visible else R.drawable.password_invisible),
                            contentDescription = "",
                            modifier = Modifier
                                .size(26.dp)
                                .clickable {
                                    showPassword = !showPassword
                                })
                        Icon(imageVector = Icons.Outlined.Clear,
                            contentDescription = "",
                            modifier = Modifier.clickable {
                                password = ""
                            })
                    }
                }
            },
            placeholder = {
                Text(text = "请输入密码...")
            },
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        SubmitActionBar(context, userName, password)
    }
}

@Composable
private fun whiteFieldColors() = TextFieldDefaults.colors(
    focusedContainerColor = Color.White, unfocusedContainerColor = Color.White
)

@Composable
private fun SubmitActionBar(
    context: Context, userName: String, password: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
    ) {
        Button(modifier = Modifier.weight(1f), onClick = {
            showToast(context, "用户名:$userName, 密码:$password")
        }) {
            Text("登录")
        }

        Button(modifier = Modifier.weight(1f), onClick = {
            showToast(context, "注册...")
        }) {
            Text("注册")
        }
    }
}


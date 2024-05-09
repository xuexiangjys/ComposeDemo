package com.xuexiang.composedemo.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.xuexiang.composedemo.navi.Screen
import com.xuexiang.composedemo.navi.navigationTo
import kotlinx.coroutines.delay
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

@Composable
fun ScrollColumnArea(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        content()
    }
}

@Composable
fun ToastComponent(
    modifier: Modifier = Modifier,
    message: String,
    backgroundColor: Color = Color.DarkGray,
    textColor: Color = Color.White,
    fontSize: TextUnit = 18.sp,
    paddingHorizontal: Dp = 20.dp,
    paddingVertical: Dp = 12.dp,
    duration: Duration = 2.seconds,
    onDismiss: () -> Unit
) {
    Column(
        modifier = modifier
            .background(
                backgroundColor, shape = RoundedCornerShape(paddingHorizontal)
            )
            .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
    ) {
        Text(text = message, color = textColor, fontSize = fontSize)
    }
    LaunchedEffect(Unit) {
        delay(duration)
        onDismiss()
    }
}

@Composable
fun DemoScreenItem(navController: NavController, screen: Screen) {
    Button(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp), onClick = {
        navController.navigationTo(screen)
    }) {
        Text(
            screen.title, fontSize = 20.sp
        )
    }
}
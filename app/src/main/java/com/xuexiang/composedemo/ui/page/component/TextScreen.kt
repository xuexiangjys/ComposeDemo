package com.xuexiang.composedemo.ui.page.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xuexiang.composedemo.R
import com.xuexiang.composedemo.ui.widget.ScrollColumnArea
import com.xuexiang.composedemo.ui.widget.showToast

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TextScreen() {
    val context = LocalContext.current

    ScrollColumnArea {
        Text(text = stringResource(id = R.string.app_name))

        Text(
            text = stringResource(id = R.string.app_name),
            // 字号
            fontSize = 25.sp,
            // 字体
            fontStyle = FontStyle.Italic,
            // 字体颜色
            color = Color.Red,
            // 字体加粗
            fontWeight = FontWeight.Bold, style = TextStyle(
                // 下划线
                textDecoration = TextDecoration.Underline,
                // 字体阴影
                shadow = Shadow(
                    color = Color.Black, offset = Offset(6.0f, 12.0f), blurRadius = 3f
                )
            ), modifier = Modifier
                .width(300.dp)
                .background(Color.LightGray),
            // 居中对齐
            textAlign = TextAlign.Center
        )

        // Span
        Text(buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Blue)) {
                append("H")
            }
            withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                append("ello ")
            }
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) {
                append("W")
            }
            append("orld")
            withStyle(
                SpanStyle(
                    // 渐变色
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color.Red, Color.Yellow, Color.Green, Color.Blue, Color.Cyan
                        )
                    )
                )
            ) {
                append(" because they are blinded.")
            }
        })

        // Marquee
        Text(
            "Learn about why it's great to use Jetpack Compose",
            modifier = Modifier.basicMarquee(),
            fontSize = 50.sp
        )

        // 溢出文字的处理
        Text("Hello World ".repeat(50), maxLines = 2, overflow = TextOverflow.Ellipsis)

        // 文字选择
        SelectionContainer {
            Column {
                Text("这段文字是可以选择的!")
                DisableSelection {
                    Text("这段文字不可以选择!")
                }
                Text("这段文字是可以选择的!")
            }
        }

        // 文字点击
        ClickableText(text = AnnotatedString("请点击我!"),
            style = TextStyle(fontSize = 20.sp),
            onClick = { offset ->
                showToast(context, "文字被点中, offset:$offset")
            })
    }
}
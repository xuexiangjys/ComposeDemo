package com.xuexiang.composedemo.ui.widget

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun showToast(context: Context, text: CharSequence) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

fun rainbowBrush() = Brush.sweepGradient(
    listOf(
        Color(0xFF9575CD),
        Color(0xFFBA68C8),
        Color(0xFFE57373),
        Color(0xFFFFB74D),
        Color(0xFFFFF176),
        Color(0xFFAED581),
        Color(0xFF4DD0E1),
        Color(0xFF9575CD)
    )
)

fun String.argument() = "{$this}"
fun String.argumentOptional() = "$this={$this}"

fun String.argumentOptionalValue(value: Any) = "$this=$value"
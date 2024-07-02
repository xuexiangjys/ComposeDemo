package com.xuexiang.composedemo.ui.widget

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import java.io.Serializable

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

//=============参数定义==================//

fun String.argument() = "{$this}"
fun getArgumentFormat(vararg keys: String) = buildString {
    keys.forEach { value ->
        append("/")
        append(value.argument())
    }
}

fun String.argumentOptional() = "$this={$this}"
fun getOptionalArgumentFormat(vararg keys: String) = buildString {
    keys.forEachIndexed { index, value ->
        append(if (index == 0) "?" else "&")
        append(value.argumentOptional())
    }
}

inline fun <reified T : Serializable> createSerializableNavType(
    isNullableAllowed: Boolean = false
): NavType<T> {
    return object : NavType<T>(isNullableAllowed) {
        override val name: String
            get() = "SupportSerializable"

        //从Bundle中检索 Serializable类型
        override fun get(bundle: Bundle, key: String): T? {
            return bundle.getSerializable(key) as? T
        }

        //作为Serializable类型添加到 Bundle
        override fun put(bundle: Bundle, key: String, value: T) {
            bundle.putSerializable(key, value)
        }

        override fun parseValue(value: String): T {  //定义传递给 String 的 Parsing 方法
            return JsonUtils.fromJson(value, T::class.java)
        }
    }
}

//=============传入参数==================//
fun argumentValue(value: Any): String {
    return if (value is Serializable || value is Parcelable) {
        "${JsonUtils.toJson(value)}"
    } else {
        "$value"
    }
}

fun String.argumentOptionalValue(value: Any): String {
    return if (value is Serializable || value is Parcelable) {
        "$this=${JsonUtils.toJson(value)}"
    } else {
        "$this=$value"
    }
}

fun putArguments(arguments: List<Any>) = buildString {
    arguments.forEach { value ->
        append("/")
        append(argumentValue(value))
    }
}

fun putOptionalArguments(arguments: Map<String, Any>) = buildString {
    arguments.entries.forEachIndexed { index, entry ->
        append(if (index == 0) "?" else "&")
        append(entry.key.argumentOptionalValue(entry.value))
    }
}
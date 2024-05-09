package com.xuexiang.composedemo.ui.widget

import android.content.Context
import android.widget.Toast

fun showToast(context: Context, text: CharSequence) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}
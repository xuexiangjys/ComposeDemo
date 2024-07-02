package com.xuexiang.composedemo.ui.widget

import com.google.gson.Gson

object JsonUtils {

    private val gson = Gson()

    fun toJson(value: Any): String = gson.toJson(value)

    fun <T> fromJson(json: String, classOfT: Class<T>): T = gson.fromJson(json, classOfT)
}
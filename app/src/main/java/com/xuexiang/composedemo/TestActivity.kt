package com.xuexiang.composedemo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.xuexiang.composedemo.databinding.ActivityTestBinding
import com.xuexiang.composedemo.navi.ARGUMENT_KEY
import com.xuexiang.composedemo.navi.ARGUMENT_KEY2

class TestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTestBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        binding.button.setOnClickListener {
            setResult(RESULT_OK, Intent().apply {
                putExtra(ARGUMENT_KEY, (Math.random() * 1000 + 88).toInt())
                putExtra(ARGUMENT_KEY2, "这是Activity返回的结果数据")
            })
            finish()
        }

        val param1 = intent.extras?.getInt(ARGUMENT_KEY)
        val param2 = intent.extras?.getString(ARGUMENT_KEY2)

        binding.content.text = "参数1:$param1, 参数2:$param2"
    }
}
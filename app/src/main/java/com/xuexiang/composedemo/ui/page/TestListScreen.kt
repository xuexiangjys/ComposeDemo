package com.xuexiang.composedemo.ui.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xuexiang.composedemo.R
import com.xuexiang.composedemo.ui.theme.ComposeDemoTheme

@Composable
fun TestListScreen() {
    val list = listOf("11", "22", "33", "aa", "bb", "cc", "dd")
    LazyColumn {
        item {
            repeat(50) {
                Greeting("Android$it")
            }
        }

        val newList = list.chunked(3)
        items(newList.size) { index ->
            VerticalGridView(newList, index) {
                GridItem(it)
            }
        }

        item {
            repeat(50) {
                Greeting("Android$it")
            }
        }
    }
}

@Composable
private fun <T> VerticalGridView(
    newList: List<List<T>>, rowIndex: Int, content: @Composable BoxScope.(item: T) -> Unit
) {
    Row {
        for ((columnIndex, item) in newList[rowIndex].withIndex()) {
            Box(
                modifier = Modifier.fillMaxWidth(1f / (3 - columnIndex)),
                contentAlignment = Alignment.Center
            ) {
                content(item)
            }
        }
    }
}

@Composable
private fun GridItem(item: String) {
    Column {
        Icon(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = item, fontSize = 15.sp)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier, fontSize = 30.sp
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeDemoTheme {
        Greeting("Android")
    }
}
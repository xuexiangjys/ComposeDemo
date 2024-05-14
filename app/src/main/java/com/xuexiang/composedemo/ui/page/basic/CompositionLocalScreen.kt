package com.xuexiang.composedemo.ui.page.basic

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xuexiang.composedemo.ui.widget.LocalCardStyle
import com.xuexiang.composedemo.ui.widget.LocalConfig
import com.xuexiang.composedemo.ui.widget.ScrollColumnArea

/***
 * CompositionLocal常用于全局的主题、样式、配置等,这样就不需要层层传递.
 */
@Composable
fun CompositionLocalScreen() {
    ScrollColumnArea {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = LocalCardStyle.current.containerColor
            ), elevation = CardDefaults.cardElevation(
                defaultElevation = LocalCardStyle.current.elevation
            )
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Config:\nisDebug=${LocalConfig.current.isDebug}\nbaseUrl=${LocalConfig.current.baseUrl}",
                color = LocalCardStyle.current.textColor
            )
        }
    }
}



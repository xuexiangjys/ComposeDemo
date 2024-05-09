package com.xuexiang.composedemo.ui.page.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.xuexiang.composedemo.ui.widget.ScrollColumnArea

@Composable
fun SwitchScreen() {
    ScrollColumnArea {
        var checked by remember { mutableStateOf(true) }
        Switch(checked = checked, onCheckedChange = {
            checked = it
        })

        Switch(checked = checked, onCheckedChange = {
            checked = it
        }, thumbContent = if (checked) {
            { CheckedIcon() }
        } else {
            null
        })

        Switch(
            checked = checked,
            onCheckedChange = {
                checked = it
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.primary,
                checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            )
        )
    }
}

@Composable
private fun CheckedIcon() {
    Icon(
        imageVector = Icons.Filled.Check,
        contentDescription = null,
        modifier = Modifier.size(SwitchDefaults.IconSize),
    )
}
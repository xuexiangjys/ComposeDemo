package com.xuexiang.composedemo.ui.page.expands

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.xuexiang.composedemo.navi.MainPages
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ScaffoldScreen() {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(
            title = {
                Text("标题")
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Yellow
            ),
            scrollBehavior = scrollBehavior,
        )
    }, floatingActionButton = {
        FloatingActionButton(onClick = { }) {
            Icon(
                Icons.Filled.Add, contentDescription = "Add Button"
            )
        }
    }, bottomBar = {
        BottomBarDemo()
    }, snackbarHost = {
        SnackbarHost(hostState = snackBarHostState)
    }) { contentPadding ->
        val pagerState = rememberPagerState { 4 }
        HorizontalPager(
            state = pagerState, modifier = Modifier.padding(contentPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(scrollBehavior.nestedScrollConnection)
            ) {
                items(30) {
                    Text(text = "列表内容:$it", modifier = Modifier
                        .clickable {
                            scope.launch {
                                snackBarHostState.showSnackbar("点击了:$it")
                            }
                        }
                        .padding(16.dp)
                        .fillMaxWidth())
                }
            }
        }
    }

}


@Composable
private fun BottomBarDemo() {
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Green),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MainPages.forEachIndexed { _, item ->
                BottomNavigationItem(icon = { Icon(item.icon, contentDescription = null) },
                    label = { Text(item.title) },
                    selectedContentColor = Color.Blue,
                    unselectedContentColor = Color.Gray,
                    selected = false,
                    onClick = {})
            }
        }
    }
}
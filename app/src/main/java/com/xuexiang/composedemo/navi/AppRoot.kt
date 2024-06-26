package com.xuexiang.composedemo.navi

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.xuexiang.composedemo.ui.widget.LocalCardStyle
import com.xuexiang.composedemo.ui.widget.LocalConfig
import com.xuexiang.composedemo.ui.widget.LocalNavController

@Composable
fun AppRoot() {
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }
    var title by remember { mutableStateOf(MainScreen.Home.title) }
    CompositionLocalProvider(
        LocalNavController provides navController
    ) {
        Scaffold(
//        topBar = {
//            CenterAlignedTopAppBar(
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primaryContainer,
//                    titleContentColor = MaterialTheme.colorScheme.primary,
//                ),
//                title = {
//                    Text(title)
//                }
//            )
//        },
            snackbarHost = {
                SnackbarHost(hostState = snackBarHostState)
            },
            bottomBar = {
                BottomAppBarComponent(navController) {
                    title = it.title
                }
//            BottomNavigationComponent(navController) {
//                title = it.title
//            }
            },
        ) { innerPadding ->
            MainNavHost(navController = navController, modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
private fun BottomAppBarComponent(
    navController: NavHostController, onPageChanged: OnPageChanged
) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
    ) {
        BottomNavigationBar(
            navController = navController,
            items = MainPages,
            modifier = Modifier.fillMaxSize(),
            itemContent = { screen, isSelected, onItemClicked ->
                BottomBarItem(
                    item = screen, isSelected = isSelected, onItemClicked = onItemClicked
                )
            },
            onPageChanged = onPageChanged
        )
    }
}

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    items: List<MainScreen>,
    itemContent: @Composable RowScope.(screen: MainScreen, isSelected: Boolean, OnPageChanged) -> Unit,
    onPageChanged: OnPageChanged
) {
    //获取当前的 NavBackStackEntry 来访问当前的 NavDestination
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Row(
        modifier = modifier.background(color = Color.White),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEachIndexed { _, screen ->
            itemContent(screen,
                currentDestination?.hierarchy?.any { it.route == screen.route } == true) {
                onPageChanged(it)
                processBottomItemClick(navController, screen)
            }
        }
    }
}

private fun processBottomItemClick(
    navController: NavHostController, screen: MainScreen
) {
    navController.popBackStack()
    //点击item时，清空栈内 popUpTo ID到栈顶之间的所有节点，避免站内节点持续增加
    navController.navigate(screen.route) {
        popUpTo(navController.graph.findStartDestination().id) {
            //跳转时保存页面状态
            saveState = true
        }
        //栈顶复用，避免重复点击同一个导航按钮，回退栈中多次创建实例
        launchSingleTop = true
        //回退时恢复页面状态
        restoreState = true
        //通过使用 saveState 和 restoreState 标志，当在底部导航项之间切换时，
        //系统会正确保存并恢复该项的状态和返回堆栈。
    }
}

@Composable
private fun BottomBarItem(
    modifier: Modifier = Modifier,
    item: MainScreen,
    isSelected: Boolean,   //是否选中
    onItemClicked: OnPageChanged,  //按钮点击监听
) {
    Column(
        modifier = modifier.clickableWithoutInteraction { onItemClicked.invoke(item) },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier.size(35.dp),
            imageVector = item.icon,
            contentDescription = item.title,
            tint = if (isSelected) Color.Blue else Color.Gray,
        )
        Text(
            text = item.title,
            color = if (isSelected) Color.Blue else Color.Gray,
            fontSize = 12.sp,
        )
    }
}


@Composable
private fun BottomNavigationComponent(
    navController: NavHostController, onPageChanged: OnPageChanged
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
    ) {
        BottomNavigationBar(
            modifier = Modifier.fillMaxWidth(),
            navController = navController,
            items = MainPages,
            itemContent = { screen, isSelected, onItemClicked ->
                BottomNavigationComponent(
                    item = screen, isSelected = isSelected, onItemClicked = onItemClicked
                )
            },
            onPageChanged = onPageChanged
        )
    }
}

@Composable
private fun RowScope.BottomNavigationComponent(
    modifier: Modifier = Modifier,
    item: MainScreen,
    isSelected: Boolean,   //是否选中
    onItemClicked: OnPageChanged,  //按钮点击监听
) {
    BottomNavigationItem(modifier = modifier,
        icon = { Icon(item.icon, contentDescription = null) },
        label = { Text(item.title) },
        selectedContentColor = Color.Blue,
        unselectedContentColor = Color.Gray,
        //与层次结构进行比较来确定是否被选中
        selected = isSelected,
        onClick = {
            onItemClicked.invoke(item)
        })
}

/**
 * clickable禁用点击涟漪效应
 */
inline fun Modifier.clickableWithoutInteraction(crossinline onClick: () -> Unit): Modifier =
    this.composed {
        clickable(
            interactionSource = remember { MutableInteractionSource() }, indication = null
        ) {
            onClick()
        }
    }

typealias OnPageChanged = (MainScreen) -> Unit
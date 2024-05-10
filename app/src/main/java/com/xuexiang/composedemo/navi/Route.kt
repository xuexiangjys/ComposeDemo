package com.xuexiang.composedemo.navi

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.xuexiang.composedemo.ui.page.basic.BasicScreen
import com.xuexiang.composedemo.ui.page.basic.ModifyScreen
import com.xuexiang.composedemo.ui.page.component.ButtonScreen
import com.xuexiang.composedemo.ui.page.component.ComponentScreen
import com.xuexiang.composedemo.ui.page.component.DialogScreen
import com.xuexiang.composedemo.ui.page.component.ImageLoadScreen
import com.xuexiang.composedemo.ui.page.component.ProgressIndicatorScreen
import com.xuexiang.composedemo.ui.page.component.SwitchScreen
import com.xuexiang.composedemo.ui.page.component.TextFieldScreen
import com.xuexiang.composedemo.ui.page.component.TextScreen
import com.xuexiang.composedemo.ui.page.home.HomeScreen
import com.xuexiang.composedemo.ui.page.home.test.CommonTestScreen
import com.xuexiang.composedemo.ui.page.home.test.ListTestScreen
import com.xuexiang.composedemo.ui.page.profile.ProfileScreen
import com.xuexiang.composedemo.ui.widget.BackAreaTemplate


open class Screen(val route: String, val title: String)

sealed class MainScreen(route: String, title: String, val icon: ImageVector) :
    Screen(route, title) {
    data object Home : MainScreen("home", "主页", Icons.Outlined.Home)
    data object Component : MainScreen("component", "组件", Icons.Outlined.Notifications)
    data object Basic : MainScreen("basic", "基础", Icons.Outlined.DateRange)
    data object Profile : MainScreen("profile", "我的", Icons.Outlined.AccountCircle)
}


sealed class HomeScreen(route: String, title: String) : Screen(route, title) {
    data object ListTest : HomeScreen("home/list_test", "列表测试")
    data object CommonTest : HomeScreen("home/common_test", "常规测试")

}

sealed class ComponentScreen(route: String, title: String) : Screen(route, title) {
    data object Text : ComponentScreen("component/text", "文本")
    data object Button : ComponentScreen("component/button", "按钮")
    data object TextField : ComponentScreen("component/text_field", "输入框")
    data object ImageLoad : ComponentScreen("component/image_load", "图片加载")
    data object ProgressIndicator : ComponentScreen("component/progress_indicator", "进度条")
    data object Switch : ComponentScreen("component/switch", "开关")
    data object Dialog : ComponentScreen("component/dialog", "对话框")
}

sealed class BasicScreen(route: String, title: String) : Screen(route, title) {
    data object Modify : BasicScreen("basic/modify", "修饰符")
}


val MainPages = listOf(
    MainScreen.Home, MainScreen.Component, MainScreen.Basic, MainScreen.Profile
)

val HomePages = listOf(
    HomeScreen.ListTest, HomeScreen.CommonTest
)

val ComponentPages = listOf(
    ComponentScreen.Text,
    ComponentScreen.Button,
    ComponentScreen.TextField,
    ComponentScreen.Dialog,
    ComponentScreen.ImageLoad,
    ComponentScreen.ProgressIndicator,
    ComponentScreen.Switch,
)

val BasicPages = listOf(
    BasicScreen.Modify
)


@Composable
fun MainNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController, startDestination = MainScreen.Home.route, modifier = modifier.fillMaxWidth()
    ) {
        composable(MainScreen.Home.route) { HomeScreen(navController) }
        composable(MainScreen.Component.route) { ComponentScreen(navController) }
        composable(MainScreen.Profile.route) { ProfileScreen(navController) }
        composable(MainScreen.Basic.route) { BasicScreen(navController) }

        composableScreen(HomeScreen.ListTest, navController) { ListTestScreen() }
        composableScreen(HomeScreen.CommonTest, navController) { CommonTestScreen() }

        composableScreen(ComponentScreen.Text, navController) { TextScreen() }
        composableScreen(ComponentScreen.Button, navController) { ButtonScreen() }
        composableScreen(ComponentScreen.TextField, navController) { TextFieldScreen() }
        composableScreen(ComponentScreen.Dialog, navController) { DialogScreen() }
        composableScreen(ComponentScreen.ImageLoad, navController) { ImageLoadScreen() }
        composableScreen(
            ComponentScreen.ProgressIndicator,
            navController
        ) { ProgressIndicatorScreen() }
        composableScreen(ComponentScreen.Switch, navController) { SwitchScreen() }

        composableScreen(BasicScreen.Modify, navController) { ModifyScreen() }
    }
}

fun NavController.navigationTo(screen: Screen) {
    navigate(screen.route)
}

fun NavGraphBuilder.composableScreen(
    screen: Screen,
    navController: NavController,
    content: @Composable ColumnScope.() -> Unit
) {
    composable(screen.route) {
        BackAreaTemplate(
            title = screen.title, navController = navController, content = content
        )
    }
}
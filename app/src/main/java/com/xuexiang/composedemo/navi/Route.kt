package com.xuexiang.composedemo.navi

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.xuexiang.composedemo.ui.page.cart.CartScreen
import com.xuexiang.composedemo.ui.page.component.ComponentScreen
import com.xuexiang.composedemo.ui.page.component.ImageLoadScreen
import com.xuexiang.composedemo.ui.page.home.HomeScreen
import com.xuexiang.composedemo.ui.page.home.test.TestListScreen
import com.xuexiang.composedemo.ui.page.profile.ProfileScreen
import com.xuexiang.composedemo.ui.widget.BackAreaTemplate


open class Screen(val route: String, val title: String)

sealed class MainScreen(route: String, title: String, val icon: ImageVector) : Screen(route, title) {
    data object Home : MainScreen("home", "主页", Icons.Outlined.Home)
    data object Component : MainScreen("component", "组件", Icons.Outlined.Notifications)
    data object Cart : MainScreen("cart", "购物车", Icons.Outlined.ShoppingCart)
    data object Profile : MainScreen("profile", "我的", Icons.Outlined.AccountCircle)
}


sealed class HomeScreen(route: String, title: String) : Screen(route, title) {
    data object TestList : HomeScreen("home/test_list", "测试列表")
}

sealed class ComponentScreen(route: String, title: String) : Screen(route, title) {
    data object ImageLoad : ComponentScreen("component/image_load", "图片加载")
}


val MainPages = listOf(
    MainScreen.Home,
    MainScreen.Component,
    MainScreen.Cart,
    MainScreen.Profile
)

val HomePages = listOf(
    HomeScreen.TestList
)

val ComponentPages = listOf(
    ComponentScreen.ImageLoad
)

@Composable
fun MainNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController, startDestination = MainScreen.Home.route, modifier = modifier.fillMaxWidth()
    ) {
        composable(MainScreen.Home.route) { HomeScreen(navController) }
        composable(MainScreen.Component.route) { ComponentScreen(navController) }
        composable(MainScreen.Profile.route) { ProfileScreen(navController) }
        composable(MainScreen.Cart.route) { CartScreen(navController) }

        composableScreen(HomeScreen.TestList, navController) { TestListScreen() }

        composableScreen(ComponentScreen.ImageLoad, navController) { ImageLoadScreen() }
    }
}

fun NavController.navigationTo(screen: Screen) {
    navigate(screen.route)
}

fun NavGraphBuilder.composableScreen(screen: Screen, navController: NavController, content: @Composable ColumnScope.() -> Unit) {
    composable(screen.route) {
        BackAreaTemplate(
            title = screen.title,
            navController = navController,
            content = content
        )
    }
}
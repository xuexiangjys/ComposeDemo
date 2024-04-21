package com.xuexiang.composedemo.navi

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.xuexiang.composedemo.ui.page.TestListScreen
import com.xuexiang.composedemo.ui.page.cart.CartScreen
import com.xuexiang.composedemo.ui.page.favorite.FavoriteScreen
import com.xuexiang.composedemo.ui.page.home.HomeScreen
import com.xuexiang.composedemo.ui.page.profile.ProfileScreen

sealed class MainScreen(val route: String, val title: String, val icon: ImageVector) {
    data object Home : MainScreen("home", "Home", Icons.Outlined.Home)
    data object Favorite : MainScreen("favorite", "Favorite", Icons.Outlined.FavoriteBorder)
    data object Cart : MainScreen("cart", "Cart", Icons.Outlined.ShoppingCart)
    data object Profile : MainScreen("profile", "Profile", Icons.Outlined.Person)
}

sealed class Screen(val route: String, val title: String) {
    data object TestList : Screen("TestList", "TestList")
}


val MainPages = listOf(
    MainScreen.Home, MainScreen.Favorite, MainScreen.Cart, MainScreen.Profile
)

@Composable
fun MainNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController, startDestination = MainScreen.Home.route, modifier = modifier.fillMaxWidth()
    ) {
        composable(MainScreen.Home.route) { HomeScreen(navController) }
        composable(MainScreen.Favorite.route) { FavoriteScreen(navController) }
        composable(MainScreen.Profile.route) { ProfileScreen(navController) }
        composable(MainScreen.Cart.route) { CartScreen(navController) }

        composable(Screen.TestList.route) { TestListScreen() }
    }
}

fun NavController.navigationTo(screen: Screen) {
    navigate(screen.route)
}
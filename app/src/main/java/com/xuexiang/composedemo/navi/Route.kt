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
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.xuexiang.composedemo.ui.page.basic.BasicScreen
import com.xuexiang.composedemo.ui.page.basic.CompositionLocalScreen
import com.xuexiang.composedemo.ui.page.basic.DefaultArgumentScreen
import com.xuexiang.composedemo.ui.page.basic.ModifyScreen
import com.xuexiang.composedemo.ui.page.basic.NavigationScreen
import com.xuexiang.composedemo.ui.page.basic.OptionalArgumentScreen
import com.xuexiang.composedemo.ui.page.basic.TypeArgumentScreen
import com.xuexiang.composedemo.ui.page.basic.User
import com.xuexiang.composedemo.ui.page.component.ButtonScreen
import com.xuexiang.composedemo.ui.page.component.ComponentScreen
import com.xuexiang.composedemo.ui.page.component.DialogScreen
import com.xuexiang.composedemo.ui.page.component.ImageLoadScreen
import com.xuexiang.composedemo.ui.page.component.PagerScreen
import com.xuexiang.composedemo.ui.page.component.ProgressIndicatorScreen
import com.xuexiang.composedemo.ui.page.component.SwitchScreen
import com.xuexiang.composedemo.ui.page.component.TextFieldScreen
import com.xuexiang.composedemo.ui.page.component.TextScreen
import com.xuexiang.composedemo.ui.page.expands.ExpandsScreen
import com.xuexiang.composedemo.ui.page.expands.NestedScrollScreen
import com.xuexiang.composedemo.ui.page.expands.ScaffoldScreen
import com.xuexiang.composedemo.ui.page.home.HomeScreen
import com.xuexiang.composedemo.ui.page.home.test.CommonTestScreen
import com.xuexiang.composedemo.ui.page.home.test.ListTestScreen
import com.xuexiang.composedemo.ui.widget.BackAreaTemplate
import com.xuexiang.composedemo.ui.widget.createSerializableNavType
import com.xuexiang.composedemo.ui.widget.getArgumentFormat
import com.xuexiang.composedemo.ui.widget.getOptionalArgumentFormat


const val ARGUMENT_KEY = "argument_key"
const val ARGUMENT_KEY2 = "argument_key2"
const val ARGUMENT_KEY3 = "argument_key3"

open class Screen(val route: String, val title: String)

sealed class MainScreen(route: String, title: String, val icon: ImageVector) :
    Screen(route, title) {
    data object Home : MainScreen("home", "主页", Icons.Outlined.Home)
    data object Component : MainScreen("component", "组件", Icons.Outlined.Notifications)
    data object Basic : MainScreen("basic", "基础", Icons.Outlined.DateRange)
    data object Expands : MainScreen("expands", "拓展", Icons.Outlined.AccountCircle)
}

sealed class TestScreen(route: String, title: String) : Screen(route, title) {
    data object DefaultArgument : TestScreen(
        "test/default_argument" + getArgumentFormat(ARGUMENT_KEY),
        "(必选参数)默认类型参数页面"
    )

    data object TypeArgument : TestScreen(
        "test/type_argument" + getArgumentFormat(ARGUMENT_KEY, ARGUMENT_KEY2),
        "(必选参数)指定类型参数页面"
    )

    data object OptionalArgument : TestScreen(
        "test/optional_argument" + getOptionalArgumentFormat(
            ARGUMENT_KEY,
            ARGUMENT_KEY2,
            ARGUMENT_KEY3
        ), "(可选参数)多种参数传递页面"
    )
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
    data object Dialog : ComponentScreen("component/dialog", "对话框")
    data object Switch : ComponentScreen("component/switch", "开关")
    data object Pager : ComponentScreen("component/pager", "分页器")
}

sealed class BasicScreen(route: String, title: String) : Screen(route, title) {
    data object Modify : BasicScreen("basic/modify", "修饰符:modifier")
    data object CompositionLocal :
        BasicScreen("basic/composition_local", "隐式数据传递:CompositionLocal")

    data object Navigation : BasicScreen("basic/navigation", "页面导航:Navigation")
}

sealed class ExpandsScreen(route: String, title: String) : Screen(route, title) {
    data object Scaffold : ExpandsScreen("expands/scaffold", "脚手架:Scaffold")
    data object NestedScroll : ExpandsScreen("expands/nestedScroll", "嵌套滑动:NestedScroll")
}


val MainPages = listOf(
    MainScreen.Home, MainScreen.Component, MainScreen.Basic, MainScreen.Expands
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
    ComponentScreen.Pager
)

val BasicPages = listOf(
    BasicScreen.Modify, BasicScreen.CompositionLocal, BasicScreen.Navigation
)

val ExpandsPages = listOf(
    ExpandsScreen.Scaffold, ExpandsScreen.NestedScroll
)

@Composable
fun MainNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController, startDestination = MainScreen.Home.route, modifier = modifier.fillMaxWidth()
    ) {
        //======MainScreen========//
        composable(MainScreen.Home.route) { HomeScreen(navController) }
        composable(MainScreen.Component.route) { ComponentScreen(navController) }
        composable(MainScreen.Basic.route) { BasicScreen(navController) }
        composable(MainScreen.Expands.route) { ExpandsScreen(navController) }

        //======TestScreen, 页面路由导航参数演示========//
        composableScreen(TestScreen.DefaultArgument, navController) { backStackEntry ->
            DefaultArgumentScreen( backStackEntry.arguments?.getString(ARGUMENT_KEY) ?: "")
        }
        composableScreen(
            TestScreen.TypeArgument,
            navController,
            arguments = listOf(navArgument(ARGUMENT_KEY2) { type = NavType.IntType })
        ) { backStackEntry ->
            TypeArgumentScreen(
                argument1 = backStackEntry.arguments?.getString(ARGUMENT_KEY) ?: "",
                argument2 = backStackEntry.arguments?.getInt(ARGUMENT_KEY2) ?: 0
            )
        }
        composableScreen(
            TestScreen.OptionalArgument, navController, arguments = listOf(
                navArgument(ARGUMENT_KEY) {
                    type = NavType.IntType
                    defaultValue = 1234
                },
                navArgument(ARGUMENT_KEY2) {
                    type = NavType.StringType
                    defaultValue = "这是参数默认值"
                },
                navArgument(ARGUMENT_KEY3) {
                    type = createSerializableNavType<User>()
                },
            )
        ) { backStackEntry ->
            OptionalArgumentScreen(
                argument = backStackEntry.arguments?.getInt(ARGUMENT_KEY) ?: 0,
                argument2 = backStackEntry.arguments?.getString(ARGUMENT_KEY2) ?: "",
                user = backStackEntry.arguments?.getSerializable(ARGUMENT_KEY3) as? User
            )
        }

        //======HomeScreen========//

        composableScreen(HomeScreen.ListTest, navController) { ListTestScreen() }
        composableScreen(HomeScreen.CommonTest, navController) { CommonTestScreen() }

        //======ComponentScreen========//

        composableScreen(ComponentScreen.Text, navController) { TextScreen() }
        composableScreen(ComponentScreen.Button, navController) { ButtonScreen() }
        composableScreen(ComponentScreen.TextField, navController) { TextFieldScreen() }
        composableScreen(ComponentScreen.Dialog, navController) { DialogScreen() }
        composableScreen(ComponentScreen.ImageLoad, navController) { ImageLoadScreen() }
        composableScreen(
            ComponentScreen.ProgressIndicator, navController
        ) { ProgressIndicatorScreen() }
        composableScreen(ComponentScreen.Switch, navController) { SwitchScreen() }
        composableScreen(ComponentScreen.Pager, navController) { PagerScreen() }

        //======BasicScreen========//

        composableScreen(BasicScreen.Modify, navController) { ModifyScreen() }
        composableScreen(BasicScreen.CompositionLocal, navController) { CompositionLocalScreen() }
        composableScreen(BasicScreen.Navigation, navController) { NavigationScreen(navController) }

        //======ExpandsScreen========//

        composableScreen(ExpandsScreen.Scaffold, navController, false) { ScaffoldScreen() }
        composableScreen(ExpandsScreen.NestedScroll, navController, false) { NestedScrollScreen() }
    }
}

fun NavController.navigationTo(screen: Screen) {
    navigate(screen.route)
}

fun NavGraphBuilder.composableScreen(
    screen: Screen,
    navController: NavController,
    hasTitle: Boolean = true,
    arguments: List<NamedNavArgument> = emptyList(),
    content: @Composable ColumnScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = screen.route, arguments = arguments
    ) { backStackEntry ->
        BackAreaTemplate(
            title = screen.title, hasTitle = hasTitle, navController = navController
        ) {
            content(backStackEntry)
        }
    }
}
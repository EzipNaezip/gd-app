package com.example.gd.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.gd.LoginScreen
import com.example.gd.Screens.*
import com.example.gd.SplashScreen
import com.example.splashscreen.navigation.Screen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.EnterMember.route) {
            EnterMemberInfoScreen(navController = navController)
        }
        composable(route = Screen.Once.route) {
            OnceScreen(navController = navController)
        }
    }
}

@Composable
fun BottomNavGraph(navController: NavHostController, startDestination: String) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = BottomScreen.Main.screenRoute) {
            MainScreen(navController = navController)
        }
        composable(route = BottomScreen.Community.screenRoute) {
            ComunityScreen(navController = navController)
        }
        composable(route = BottomScreen.My.screenRoute) {
            MyScreen(navController = navController)
        }
        composable(route = BottomScreen.Setting.screenRoute) {
            SettingScreen(navController = navController)
        }
        composable(route = BottomScreen.Detail.screenRoute) {
            DetailScreen(navController = navController)
        }
        composable(route = BottomScreen.User.screenRoute) {
            UserScreen(navController = navController)
        }
    }
}

@Composable
fun BottomNavigation(navController: NavHostController) {
    val items = listOf(
        BottomScreen.Main,
        BottomScreen.Community,
        BottomScreen.My,
        BottomScreen.Setting
    )

    BottomNavigation{
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = if (currentRoute == item.screenRoute) item.iconSolid else item.iconOutline,
                        contentDescription = item.title,
                        modifier = Modifier
                            .width(26.dp)
                            .height(26.dp)
                    )
                },
                label = { Text(item.title, fontSize = 11.sp) },
                selectedContentColor = MaterialTheme.colors.primaryVariant,
                unselectedContentColor = MaterialTheme.colors.secondary,
                selected = currentRoute == item.screenRoute,
                alwaysShowLabel = false,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun MainScreenView(startDestination: String) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) }
    ) {
        Box(Modifier.padding(it)) {
            BottomNavGraph(navController = navController, startDestination)
        }
    }
}
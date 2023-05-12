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
import com.example.gd.LoginScreen
import com.example.gd.Screens.*
import com.example.gd.SplashScreen

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
fun BottomNavigation(navController: NavHostController) {
    val items = listOf(
        BottomScreen.Main,
        BottomScreen.Community,
        BottomScreen.My,
        BottomScreen.Setting
    )

    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = if (currentRoute?.startsWith(item.screenRoute) == true) item.iconSolid else item.iconOutline,
                        contentDescription = item.title,
                        modifier = Modifier
                            .width(26.dp)
                            .height(26.dp)
                    )
                },
                label = { Text(item.title, fontSize = 11.sp) },
                selectedContentColor = MaterialTheme.colors.primaryVariant,
                unselectedContentColor = MaterialTheme.colors.secondary,
                selected = currentRoute?.startsWith(item.screenRoute) == true,
                alwaysShowLabel = false,
                onClick = {
                    try {
                        navController.navigate(item.screenRoute) {
                            if (currentRoute?.startsWith(item.screenRoute) != true) {
                                navController.graph.startDestinationRoute?.let {
                                    popUpTo(it) { saveState = true }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    } catch (e: java.lang.Exception){
                    navController.navigate(item.screenRoute)
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
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) {
        Box(Modifier.padding(it)) {
            NavHost(
                navController = navController,
                startDestination = startDestination,
            ) {
                composable(MainNavigationScreens.Main.route) { MainScreen(navController = navController) }
                composable(MainNavigationScreens.Detail.route) {
                    DetailScreen(
                        navController = navController,
                        BottomScreen.Main.screenRoute
                    )
                }
                composable(MainNavigationScreens.User.route) {
                    UserScreen(
                        navController = navController,
                        BottomScreen.Main.screenRoute
                    )
                }

                composable(CommunityNavigationScreens.Community.route) {
                    ComunityScreen(
                        navController = navController
                    )
                }
                composable(CommunityNavigationScreens.Detail.route) {
                    DetailScreen(
                        navController = navController,
                        BottomScreen.Community.screenRoute
                    )
                }
                composable(CommunityNavigationScreens.User.route) {
                    UserScreen(
                        navController = navController,
                        BottomScreen.Community.screenRoute
                    )
                }

                composable(MyNavigationScreens.My.route) { MyScreen(navController = navController) }
                composable(MyNavigationScreens.Detail.route) {
                    DetailScreen(
                        navController = navController,
                        BottomScreen.My.screenRoute
                    )
                }
                composable(MyNavigationScreens.User.route) {
                    UserScreen(
                        navController = navController,
                        BottomScreen.My.screenRoute
                    )
                }

                composable(SettingNavigationScreens.Setting.route) { SettingScreen(navController = navController) }
            }
        }
    }
}
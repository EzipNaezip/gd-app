package com.dongminpark.projectgdn.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Login : Screen("login_screen")
    object EnterMember : Screen("enterMember_screen")
    object Once : Screen("once_screen")
}
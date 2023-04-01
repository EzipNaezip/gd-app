package com.example.splashscreen.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Login : Screen("login_screen")
    object Once : Screen("once_screen")
}
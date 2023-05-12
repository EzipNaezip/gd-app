package com.example.gd.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.gd.ui.IconPack
import com.example.gd.ui.iconpack.*

sealed class BottomScreen(
    val title: String,
    val iconOutline: ImageVector,
    val iconSolid: ImageVector,
    val screenRoute: String
) {
    object Main : BottomScreen("메인", IconPack.HomeOutline, IconPack.HomeSolid, "main")

    object Community :
        BottomScreen("커뮤니티", IconPack.CommunityOutline, IconPack.CommunitySolid, "community")

    object My : BottomScreen("마이페이지", IconPack.UserOutline, IconPack.UserSolid, "my")

    object Setting :
        BottomScreen("설정", IconPack.SettingOutline, IconPack.SettingSolid, "setting")
}

sealed class MainNavigationScreens(val screenRoute: String, val route: String) {
    object Main : MainNavigationScreens("main","main")
    object Detail : MainNavigationScreens("main", "main_detail_screen")
    object User : MainNavigationScreens( "main","main_user_screen")
}

sealed class CommunityNavigationScreens(val screenRoute: String, val route: String) {
    object Community : CommunityNavigationScreens("community","community")
    object Detail : CommunityNavigationScreens("community", "community_detail_screen")
    object User : CommunityNavigationScreens( "community","community_user_screen")
}

sealed class MyNavigationScreens(val screenRoute: String, val route: String = "my") {
    object My : MyNavigationScreens("my","my")
    object Detail : MyNavigationScreens("my", "my_detail_screen")
    object User : MyNavigationScreens( "my","my_user_screen")
}

sealed class SettingNavigationScreens(val screenRoute: String, val route: String) {
    object Setting : SettingNavigationScreens("setting","setting")
}
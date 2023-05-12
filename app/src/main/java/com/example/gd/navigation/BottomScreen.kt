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

sealed class MainNavigationScreens(val route: String) {
    object Main : MainNavigationScreens("main")
    object Detail : MainNavigationScreens("main_detail_screen")
    object User : MainNavigationScreens("main_user_screen")
}

sealed class CommunityNavigationScreens(val route: String) {
    object Community : CommunityNavigationScreens("community")
    object Detail : CommunityNavigationScreens("community_detail_screen")
    object User : CommunityNavigationScreens("community_user_screen")
}

sealed class MyNavigationScreens(val route: String = "my") {
    object My : MyNavigationScreens("my")
    object Detail : MyNavigationScreens("my_detail_screen")
    object User : MyNavigationScreens("my_user_screen")
}

sealed class SettingNavigationScreens(val route: String) {
    object Setting : SettingNavigationScreens("setting")
}
package com.example.gd.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.gd.ui.IconPack
import com.example.gd.ui.iconpack.*

sealed class BottomScreen(
    val title: String, val icon: ImageVector, val screenRoute: String
) {
    object Main : BottomScreen("메인", IconPack.Home,"main_screen")
    object My : BottomScreen("마이페이지", IconPack.UserCircle,"my_screen")
    object Community : BottomScreen("커뮤니티", IconPack.Comunity,"community_screen")
    object Setting : BottomScreen("설정", IconPack.Setting,"setting_screen")
}
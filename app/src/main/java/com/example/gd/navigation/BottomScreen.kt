package com.example.gd.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.gd.ui.IconPack
import com.example.gd.ui.iconpack.*

sealed class BottomScreen(
    val title: String, val iconOutline: ImageVector, val iconSolid: ImageVector, val screenRoute: String
) {
    object Main : BottomScreen("메인", IconPack.HomeOutline, IconPack.HomeSolid, "main_screen")
    object My : BottomScreen("마이페이지", IconPack.UserOutline, IconPack.UserSolid, "my_screen")
    object Community : BottomScreen("커뮤니티", IconPack.CommunityOutline, IconPack.CommunitySolid,"community_screen")
    object Setting : BottomScreen("설정", IconPack.SettingOutline, IconPack.SettingSolid,"setting_screen")
    object Detail : BottomScreen("디테일", IconPack.CommunityOutline, IconPack.CommunitySolid, "detail_screen")
    object User : BottomScreen("유저", IconPack.CommunityOutline, IconPack.CommunitySolid, "user_screen")
}
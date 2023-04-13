package com.example.gd.ui

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.gd.ui.iconpack.BookmarkOutline
import com.example.gd.ui.iconpack.BookmarkSolid
import com.example.gd.ui.iconpack.CommunityOutline
import com.example.gd.ui.iconpack.CommunitySolid
import com.example.gd.ui.iconpack.Download
import com.example.gd.ui.iconpack.FavoriteOutline
import com.example.gd.ui.iconpack.FavoriteSolid
import com.example.gd.ui.iconpack.GoogleLogin
import com.example.gd.ui.iconpack.HomeOutline
import com.example.gd.ui.iconpack.HomeSolid
import com.example.gd.ui.iconpack.KakaoLogin
import com.example.gd.ui.iconpack.Left
import com.example.gd.ui.iconpack.Right
import com.example.gd.ui.iconpack.SettingOutline
import com.example.gd.ui.iconpack.SettingSolid
import com.example.gd.ui.iconpack.Share
import com.example.gd.ui.iconpack.SquareOutline
import com.example.gd.ui.iconpack.SquareSolid
import com.example.gd.ui.iconpack.UserOutline
import com.example.gd.ui.iconpack.UserSolid
import com.example.gd.ui.iconpack.XMark
import kotlin.collections.List as ____KtList

public object IconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val IconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(BookmarkOutline, XMark, KakaoLogin, CommunitySolid, Download, SettingSolid,
        SettingOutline, UserOutline, SquareSolid, SquareOutline, Right, FavoriteSolid, GoogleLogin,
        UserSolid, BookmarkSolid, Share, HomeOutline, CommunityOutline, HomeSolid, FavoriteOutline,
        Left)
    return __AllIcons!!
  }

package com.example.gd.ui

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.gd.ui.iconpack.Comunity
import com.example.gd.ui.iconpack.Home
import com.example.gd.ui.iconpack.Setting
import com.example.gd.ui.iconpack.User
import com.example.gd.ui.iconpack.UserCircle
import kotlin.collections.List as ____KtList

public object IconPack

private var __Icons: ____KtList<ImageVector>? = null

public val IconPack.Icons: ____KtList<ImageVector>
  get() {
    if (__Icons != null) {
      return __Icons!!
    }
    __Icons= listOf(User, Home, Comunity, Setting, UserCircle)
    return __Icons!!
  }

package com.ezipnaezip.gd.ui.iconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.dongminpark.projectgd.ui.IconPack

public val IconPack.FavoriteSolid: ImageVector
    get() {
        if (_favoriteSolid != null) {
            return _favoriteSolid!!
        }
        _favoriteSolid = Builder(name = "FavoriteSolid", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(11.645f, 20.91f)
                lineToRelative(-0.007f, -0.003f)
                lineToRelative(-0.022f, -0.012f)
                arcToRelative(15.247f, 15.247f, 0.0f, false, true, -0.383f, -0.218f)
                arcToRelative(25.18f, 25.18f, 0.0f, false, true, -4.244f, -3.17f)
                curveTo(4.688f, 15.36f, 2.25f, 12.174f, 2.25f, 8.25f)
                curveTo(2.25f, 5.322f, 4.714f, 3.0f, 7.688f, 3.0f)
                arcTo(5.5f, 5.5f, 0.0f, false, true, 12.0f, 5.052f)
                arcTo(5.5f, 5.5f, 0.0f, false, true, 16.313f, 3.0f)
                curveToRelative(2.973f, 0.0f, 5.437f, 2.322f, 5.437f, 5.25f)
                curveToRelative(0.0f, 3.925f, -2.438f, 7.111f, -4.739f, 9.256f)
                arcToRelative(25.175f, 25.175f, 0.0f, false, true, -4.244f, 3.17f)
                arcToRelative(15.247f, 15.247f, 0.0f, false, true, -0.383f, 0.219f)
                lineToRelative(-0.022f, 0.012f)
                lineToRelative(-0.007f, 0.004f)
                lineToRelative(-0.003f, 0.001f)
                arcToRelative(0.752f, 0.752f, 0.0f, false, true, -0.704f, 0.0f)
                lineToRelative(-0.003f, -0.001f)
                close()
            }
        }
        .build()
        return _favoriteSolid!!
    }

private var _favoriteSolid: ImageVector? = null

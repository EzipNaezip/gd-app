package com.example.gd.ui.iconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.example.gd.ui.IconPack

public val IconPack.FavoriteOutline: ImageVector
    get() {
        if (_favoriteOutline != null) {
            return _favoriteOutline!!
        }
        _favoriteOutline = Builder(name = "FavoriteOutline", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(21.0f, 8.25f)
                curveToRelative(0.0f, -2.485f, -2.099f, -4.5f, -4.688f, -4.5f)
                curveToRelative(-1.935f, 0.0f, -3.597f, 1.126f, -4.312f, 2.733f)
                curveToRelative(-0.715f, -1.607f, -2.377f, -2.733f, -4.313f, -2.733f)
                curveTo(5.1f, 3.75f, 3.0f, 5.765f, 3.0f, 8.25f)
                curveToRelative(0.0f, 7.22f, 9.0f, 12.0f, 9.0f, 12.0f)
                reflectiveCurveToRelative(9.0f, -4.78f, 9.0f, -12.0f)
                close()
            }
        }
        .build()
        return _favoriteOutline!!
    }

private var _favoriteOutline: ImageVector? = null

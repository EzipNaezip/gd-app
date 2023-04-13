package com.example.gd.ui.iconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.example.gd.ui.IconPack

public val IconPack.UserSolid: ImageVector
    get() {
        if (_userSolid != null) {
            return _userSolid!!
        }
        _userSolid = Builder(name = "UserSolid", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(18.685f, 19.097f)
                arcTo(9.723f, 9.723f, 0.0f, false, false, 21.75f, 12.0f)
                curveToRelative(0.0f, -5.385f, -4.365f, -9.75f, -9.75f, -9.75f)
                reflectiveCurveTo(2.25f, 6.615f, 2.25f, 12.0f)
                arcToRelative(9.723f, 9.723f, 0.0f, false, false, 3.065f, 7.097f)
                arcTo(9.716f, 9.716f, 0.0f, false, false, 12.0f, 21.75f)
                arcToRelative(9.716f, 9.716f, 0.0f, false, false, 6.685f, -2.653f)
                close()
                moveTo(6.145f, 17.812f)
                arcTo(7.486f, 7.486f, 0.0f, false, true, 12.0f, 15.0f)
                arcToRelative(7.486f, 7.486f, 0.0f, false, true, 5.855f, 2.812f)
                arcTo(8.224f, 8.224f, 0.0f, false, true, 12.0f, 20.25f)
                arcToRelative(8.224f, 8.224f, 0.0f, false, true, -5.855f, -2.438f)
                close()
                moveTo(15.75f, 9.0f)
                arcToRelative(3.75f, 3.75f, 0.0f, true, true, -7.5f, 0.0f)
                arcToRelative(3.75f, 3.75f, 0.0f, false, true, 7.5f, 0.0f)
                close()
            }
        }
        .build()
        return _userSolid!!
    }

private var _userSolid: ImageVector? = null

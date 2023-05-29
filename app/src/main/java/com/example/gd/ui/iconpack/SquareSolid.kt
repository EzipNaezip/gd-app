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

public val IconPack.SquareSolid: ImageVector
    get() {
        if (_squareSolid != null) {
            return _squareSolid!!
        }
        _squareSolid = Builder(name = "SquareSolid", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(4.5f, 7.5f)
                arcToRelative(3.0f, 3.0f, 0.0f, false, true, 3.0f, -3.0f)
                horizontalLineToRelative(9.0f)
                arcToRelative(3.0f, 3.0f, 0.0f, false, true, 3.0f, 3.0f)
                verticalLineToRelative(9.0f)
                arcToRelative(3.0f, 3.0f, 0.0f, false, true, -3.0f, 3.0f)
                horizontalLineToRelative(-9.0f)
                arcToRelative(3.0f, 3.0f, 0.0f, false, true, -3.0f, -3.0f)
                verticalLineToRelative(-9.0f)
                close()
            }
        }
        .build()
        return _squareSolid!!
    }

private var _squareSolid: ImageVector? = null

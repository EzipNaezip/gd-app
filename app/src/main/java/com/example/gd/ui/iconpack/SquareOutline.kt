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

public val IconPack.SquareOutline: ImageVector
    get() {
        if (_squareOutline != null) {
            return _squareOutline!!
        }
        _squareOutline = Builder(name = "SquareOutline", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(5.25f, 7.5f)
                arcTo(2.25f, 2.25f, 0.0f, false, true, 7.5f, 5.25f)
                horizontalLineToRelative(9.0f)
                arcToRelative(2.25f, 2.25f, 0.0f, false, true, 2.25f, 2.25f)
                verticalLineToRelative(9.0f)
                arcToRelative(2.25f, 2.25f, 0.0f, false, true, -2.25f, 2.25f)
                horizontalLineToRelative(-9.0f)
                arcToRelative(2.25f, 2.25f, 0.0f, false, true, -2.25f, -2.25f)
                verticalLineToRelative(-9.0f)
                close()
            }
        }
        .build()
        return _squareOutline!!
    }

private var _squareOutline: ImageVector? = null

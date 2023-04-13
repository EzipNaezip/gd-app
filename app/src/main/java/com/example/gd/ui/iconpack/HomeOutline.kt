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

public val IconPack.HomeOutline: ImageVector
    get() {
        if (_homeOutline != null) {
            return _homeOutline!!
        }
        _homeOutline = Builder(name = "HomeOutline", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(2.25f, 12.0f)
                lineToRelative(8.954f, -8.955f)
                curveToRelative(0.44f, -0.439f, 1.152f, -0.439f, 1.591f, 0.0f)
                lineTo(21.75f, 12.0f)
                moveTo(4.5f, 9.75f)
                verticalLineToRelative(10.125f)
                curveToRelative(0.0f, 0.621f, 0.504f, 1.125f, 1.125f, 1.125f)
                horizontalLineTo(9.75f)
                verticalLineToRelative(-4.875f)
                curveToRelative(0.0f, -0.621f, 0.504f, -1.125f, 1.125f, -1.125f)
                horizontalLineToRelative(2.25f)
                curveToRelative(0.621f, 0.0f, 1.125f, 0.504f, 1.125f, 1.125f)
                verticalLineTo(21.0f)
                horizontalLineToRelative(4.125f)
                curveToRelative(0.621f, 0.0f, 1.125f, -0.504f, 1.125f, -1.125f)
                verticalLineTo(9.75f)
                moveTo(8.25f, 21.0f)
                horizontalLineToRelative(8.25f)
            }
        }
        .build()
        return _homeOutline!!
    }

private var _homeOutline: ImageVector? = null

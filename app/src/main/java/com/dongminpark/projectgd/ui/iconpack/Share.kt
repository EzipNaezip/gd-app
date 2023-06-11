package com.ezipnaezip.gd.ui.iconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.dongminpark.projectgd.ui.IconPack

public val IconPack.Share: ImageVector
    get() {
        if (_share != null) {
            return _share!!
        }
        _share = Builder(name = "Share", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(7.217f, 10.907f)
                arcToRelative(2.25f, 2.25f, 0.0f, true, false, 0.0f, 2.186f)
                moveToRelative(0.0f, -2.186f)
                curveToRelative(0.18f, 0.324f, 0.283f, 0.696f, 0.283f, 1.093f)
                reflectiveCurveToRelative(-0.103f, 0.77f, -0.283f, 1.093f)
                moveToRelative(0.0f, -2.186f)
                lineToRelative(9.566f, -5.314f)
                moveToRelative(-9.566f, 7.5f)
                lineToRelative(9.566f, 5.314f)
                moveToRelative(0.0f, 0.0f)
                arcToRelative(2.25f, 2.25f, 0.0f, true, false, 3.935f, 2.186f)
                arcToRelative(2.25f, 2.25f, 0.0f, false, false, -3.935f, -2.186f)
                close()
                moveTo(16.783f, 5.593f)
                arcToRelative(2.25f, 2.25f, 0.0f, true, false, 3.933f, -2.185f)
                arcToRelative(2.25f, 2.25f, 0.0f, false, false, -3.933f, 2.185f)
                close()
            }
        }
        .build()
        return _share!!
    }

private var _share: ImageVector? = null

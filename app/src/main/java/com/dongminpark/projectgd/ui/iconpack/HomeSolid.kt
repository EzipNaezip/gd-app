package com.ezipnaezip.gd.ui.iconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.dongminpark.projectgd.ui.IconPack

public val IconPack.HomeSolid: ImageVector
    get() {
        if (_homeSolid != null) {
            return _homeSolid!!
        }
        _homeSolid = Builder(name = "HomeSolid", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(11.47f, 3.84f)
                arcToRelative(0.75f, 0.75f, 0.0f, false, true, 1.06f, 0.0f)
                lineToRelative(8.69f, 8.69f)
                arcToRelative(0.75f, 0.75f, 0.0f, true, false, 1.06f, -1.06f)
                lineToRelative(-8.689f, -8.69f)
                arcToRelative(2.25f, 2.25f, 0.0f, false, false, -3.182f, 0.0f)
                lineToRelative(-8.69f, 8.69f)
                arcToRelative(0.75f, 0.75f, 0.0f, false, false, 1.061f, 1.06f)
                lineToRelative(8.69f, -8.69f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(12.0f, 5.432f)
                lineToRelative(8.159f, 8.159f)
                curveToRelative(0.03f, 0.03f, 0.06f, 0.058f, 0.091f, 0.086f)
                verticalLineToRelative(6.198f)
                curveToRelative(0.0f, 1.035f, -0.84f, 1.875f, -1.875f, 1.875f)
                horizontalLineTo(15.0f)
                arcToRelative(0.75f, 0.75f, 0.0f, false, true, -0.75f, -0.75f)
                verticalLineToRelative(-4.5f)
                arcToRelative(0.75f, 0.75f, 0.0f, false, false, -0.75f, -0.75f)
                horizontalLineToRelative(-3.0f)
                arcToRelative(0.75f, 0.75f, 0.0f, false, false, -0.75f, 0.75f)
                verticalLineTo(21.0f)
                arcToRelative(0.75f, 0.75f, 0.0f, false, true, -0.75f, 0.75f)
                horizontalLineTo(5.625f)
                arcToRelative(1.875f, 1.875f, 0.0f, false, true, -1.875f, -1.875f)
                verticalLineToRelative(-6.198f)
                arcToRelative(2.29f, 2.29f, 0.0f, false, false, 0.091f, -0.086f)
                lineTo(12.0f, 5.43f)
                close()
            }
        }
        .build()
        return _homeSolid!!
    }

private var _homeSolid: ImageVector? = null

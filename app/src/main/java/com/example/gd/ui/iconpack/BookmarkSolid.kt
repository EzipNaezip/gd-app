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

public val IconPack.BookmarkSolid: ImageVector
    get() {
        if (_bookmarkSolid != null) {
            return _bookmarkSolid!!
        }
        _bookmarkSolid = Builder(name = "BookmarkSolid", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(6.32f, 2.577f)
                arcToRelative(49.255f, 49.255f, 0.0f, false, true, 11.36f, 0.0f)
                curveToRelative(1.497f, 0.174f, 2.57f, 1.46f, 2.57f, 2.93f)
                verticalLineTo(21.0f)
                arcToRelative(0.75f, 0.75f, 0.0f, false, true, -1.085f, 0.67f)
                lineTo(12.0f, 18.089f)
                lineToRelative(-7.165f, 3.583f)
                arcTo(0.75f, 0.75f, 0.0f, false, true, 3.75f, 21.0f)
                verticalLineTo(5.507f)
                curveToRelative(0.0f, -1.47f, 1.073f, -2.756f, 2.57f, -2.93f)
                close()
            }
        }
        .build()
        return _bookmarkSolid!!
    }

private var _bookmarkSolid: ImageVector? = null

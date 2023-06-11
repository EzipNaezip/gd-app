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

public val IconPack.BookmarkOutline: ImageVector
    get() {
        if (_bookmarkOutline != null) {
            return _bookmarkOutline!!
        }
        _bookmarkOutline = Builder(name = "BookmarkOutline", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(17.593f, 3.322f)
                curveToRelative(1.1f, 0.128f, 1.907f, 1.077f, 1.907f, 2.185f)
                verticalLineTo(21.0f)
                lineTo(12.0f, 17.25f)
                lineTo(4.5f, 21.0f)
                verticalLineTo(5.507f)
                curveToRelative(0.0f, -1.108f, 0.806f, -2.057f, 1.907f, -2.185f)
                arcToRelative(48.507f, 48.507f, 0.0f, false, true, 11.186f, 0.0f)
                close()
            }
        }
        .build()
        return _bookmarkOutline!!
    }

private var _bookmarkOutline: ImageVector? = null

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

public val IconPack.Download: ImageVector
    get() {
        if (_download != null) {
            return _download!!
        }
        _download = Builder(name = "Download", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(3.0f, 16.5f)
                verticalLineToRelative(2.25f)
                arcTo(2.25f, 2.25f, 0.0f, false, false, 5.25f, 21.0f)
                horizontalLineToRelative(13.5f)
                arcTo(2.25f, 2.25f, 0.0f, false, false, 21.0f, 18.75f)
                verticalLineTo(16.5f)
                moveTo(16.5f, 12.0f)
                lineTo(12.0f, 16.5f)
                moveToRelative(0.0f, 0.0f)
                lineTo(7.5f, 12.0f)
                moveToRelative(4.5f, 4.5f)
                verticalLineTo(3.0f)
            }
        }
        .build()
        return _download!!
    }

private var _download: ImageVector? = null

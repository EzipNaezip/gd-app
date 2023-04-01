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

public val IconPack.Comunity: ImageVector
    get() {
        if (_comunity != null) {
            return _comunity!!
        }
        _comunity = Builder(name = "Comunity", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(3.75f, 6.0f)
                arcTo(2.25f, 2.25f, 0.0f, false, true, 6.0f, 3.75f)
                horizontalLineToRelative(2.25f)
                arcTo(2.25f, 2.25f, 0.0f, false, true, 10.5f, 6.0f)
                verticalLineToRelative(2.25f)
                arcToRelative(2.25f, 2.25f, 0.0f, false, true, -2.25f, 2.25f)
                horizontalLineTo(6.0f)
                arcToRelative(2.25f, 2.25f, 0.0f, false, true, -2.25f, -2.25f)
                verticalLineTo(6.0f)
                close()
                moveTo(3.75f, 15.75f)
                arcTo(2.25f, 2.25f, 0.0f, false, true, 6.0f, 13.5f)
                horizontalLineToRelative(2.25f)
                arcToRelative(2.25f, 2.25f, 0.0f, false, true, 2.25f, 2.25f)
                verticalLineTo(18.0f)
                arcToRelative(2.25f, 2.25f, 0.0f, false, true, -2.25f, 2.25f)
                horizontalLineTo(6.0f)
                arcTo(2.25f, 2.25f, 0.0f, false, true, 3.75f, 18.0f)
                verticalLineToRelative(-2.25f)
                close()
                moveTo(13.5f, 6.0f)
                arcToRelative(2.25f, 2.25f, 0.0f, false, true, 2.25f, -2.25f)
                horizontalLineTo(18.0f)
                arcTo(2.25f, 2.25f, 0.0f, false, true, 20.25f, 6.0f)
                verticalLineToRelative(2.25f)
                arcTo(2.25f, 2.25f, 0.0f, false, true, 18.0f, 10.5f)
                horizontalLineToRelative(-2.25f)
                arcToRelative(2.25f, 2.25f, 0.0f, false, true, -2.25f, -2.25f)
                verticalLineTo(6.0f)
                close()
                moveTo(13.5f, 15.75f)
                arcToRelative(2.25f, 2.25f, 0.0f, false, true, 2.25f, -2.25f)
                horizontalLineTo(18.0f)
                arcToRelative(2.25f, 2.25f, 0.0f, false, true, 2.25f, 2.25f)
                verticalLineTo(18.0f)
                arcTo(2.25f, 2.25f, 0.0f, false, true, 18.0f, 20.25f)
                horizontalLineToRelative(-2.25f)
                arcTo(2.25f, 2.25f, 0.0f, false, true, 13.5f, 18.0f)
                verticalLineToRelative(-2.25f)
                close()
            }
        }
        .build()
        return _comunity!!
    }

private var _comunity: ImageVector? = null

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

public val IconPack.User: ImageVector
    get() {
        if (_user != null) {
            return _user!!
        }
        _user = Builder(name = "User", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(15.75f, 6.0f)
                arcToRelative(3.75f, 3.75f, 0.0f, true, true, -7.5f, 0.0f)
                arcToRelative(3.75f, 3.75f, 0.0f, false, true, 7.5f, 0.0f)
                close()
                moveTo(4.501f, 20.118f)
                arcToRelative(7.5f, 7.5f, 0.0f, false, true, 14.998f, 0.0f)
                arcTo(17.933f, 17.933f, 0.0f, false, true, 12.0f, 21.75f)
                curveToRelative(-2.676f, 0.0f, -5.216f, -0.584f, -7.499f, -1.632f)
                close()
            }
        }
        .build()
        return _user!!
    }

private var _user: ImageVector? = null

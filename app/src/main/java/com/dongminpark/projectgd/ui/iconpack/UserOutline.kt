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

public val IconPack.UserOutline: ImageVector
    get() {
        if (_userOutline != null) {
            return _userOutline!!
        }
        _userOutline = Builder(name = "UserOutline", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(17.982f, 18.725f)
                arcTo(7.488f, 7.488f, 0.0f, false, false, 12.0f, 15.75f)
                arcToRelative(7.488f, 7.488f, 0.0f, false, false, -5.982f, 2.975f)
                moveToRelative(11.963f, 0.0f)
                arcToRelative(9.0f, 9.0f, 0.0f, true, false, -11.963f, 0.0f)
                moveToRelative(11.963f, 0.0f)
                arcTo(8.966f, 8.966f, 0.0f, false, true, 12.0f, 21.0f)
                arcToRelative(8.966f, 8.966f, 0.0f, false, true, -5.982f, -2.275f)
                moveTo(15.0f, 9.75f)
                arcToRelative(3.0f, 3.0f, 0.0f, true, true, -6.0f, 0.0f)
                arcToRelative(3.0f, 3.0f, 0.0f, false, true, 6.0f, 0.0f)
                close()
            }
        }
        .build()
        return _userOutline!!
    }

private var _userOutline: ImageVector? = null

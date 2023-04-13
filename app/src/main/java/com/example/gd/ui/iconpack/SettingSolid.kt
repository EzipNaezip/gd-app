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

public val IconPack.SettingSolid: ImageVector
    get() {
        if (_settingSolid != null) {
            return _settingSolid!!
        }
        _settingSolid = Builder(name = "SettingSolid", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(11.078f, 2.25f)
                curveToRelative(-0.917f, 0.0f, -1.699f, 0.663f, -1.85f, 1.567f)
                lineTo(9.05f, 4.889f)
                curveToRelative(-0.02f, 0.12f, -0.115f, 0.26f, -0.297f, 0.348f)
                arcToRelative(7.493f, 7.493f, 0.0f, false, false, -0.986f, 0.57f)
                curveToRelative(-0.166f, 0.115f, -0.334f, 0.126f, -0.45f, 0.083f)
                lineTo(6.3f, 5.508f)
                arcToRelative(1.875f, 1.875f, 0.0f, false, false, -2.282f, 0.819f)
                lineToRelative(-0.922f, 1.597f)
                arcToRelative(1.875f, 1.875f, 0.0f, false, false, 0.432f, 2.385f)
                lineToRelative(0.84f, 0.692f)
                curveToRelative(0.095f, 0.078f, 0.17f, 0.229f, 0.154f, 0.43f)
                arcToRelative(7.598f, 7.598f, 0.0f, false, false, 0.0f, 1.139f)
                curveToRelative(0.015f, 0.2f, -0.059f, 0.352f, -0.153f, 0.43f)
                lineToRelative(-0.841f, 0.692f)
                arcToRelative(1.875f, 1.875f, 0.0f, false, false, -0.432f, 2.385f)
                lineToRelative(0.922f, 1.597f)
                arcToRelative(1.875f, 1.875f, 0.0f, false, false, 2.282f, 0.818f)
                lineToRelative(1.019f, -0.382f)
                curveToRelative(0.115f, -0.043f, 0.283f, -0.031f, 0.45f, 0.082f)
                curveToRelative(0.312f, 0.214f, 0.641f, 0.405f, 0.985f, 0.57f)
                curveToRelative(0.182f, 0.088f, 0.277f, 0.228f, 0.297f, 0.35f)
                lineToRelative(0.178f, 1.071f)
                curveToRelative(0.151f, 0.904f, 0.933f, 1.567f, 1.85f, 1.567f)
                horizontalLineToRelative(1.844f)
                curveToRelative(0.916f, 0.0f, 1.699f, -0.663f, 1.85f, -1.567f)
                lineToRelative(0.178f, -1.072f)
                curveToRelative(0.02f, -0.12f, 0.114f, -0.26f, 0.297f, -0.349f)
                curveToRelative(0.344f, -0.165f, 0.673f, -0.356f, 0.985f, -0.57f)
                curveToRelative(0.167f, -0.114f, 0.335f, -0.125f, 0.45f, -0.082f)
                lineToRelative(1.02f, 0.382f)
                arcToRelative(1.875f, 1.875f, 0.0f, false, false, 2.28f, -0.819f)
                lineToRelative(0.923f, -1.597f)
                arcToRelative(1.875f, 1.875f, 0.0f, false, false, -0.432f, -2.385f)
                lineToRelative(-0.84f, -0.692f)
                curveToRelative(-0.095f, -0.078f, -0.17f, -0.229f, -0.154f, -0.43f)
                arcToRelative(7.614f, 7.614f, 0.0f, false, false, 0.0f, -1.139f)
                curveToRelative(-0.016f, -0.2f, 0.059f, -0.352f, 0.153f, -0.43f)
                lineToRelative(0.84f, -0.692f)
                curveToRelative(0.708f, -0.582f, 0.891f, -1.59f, 0.433f, -2.385f)
                lineToRelative(-0.922f, -1.597f)
                arcToRelative(1.875f, 1.875f, 0.0f, false, false, -2.282f, -0.818f)
                lineToRelative(-1.02f, 0.382f)
                curveToRelative(-0.114f, 0.043f, -0.282f, 0.031f, -0.449f, -0.083f)
                arcToRelative(7.49f, 7.49f, 0.0f, false, false, -0.985f, -0.57f)
                curveToRelative(-0.183f, -0.087f, -0.277f, -0.227f, -0.297f, -0.348f)
                lineToRelative(-0.179f, -1.072f)
                arcToRelative(1.875f, 1.875f, 0.0f, false, false, -1.85f, -1.567f)
                horizontalLineToRelative(-1.843f)
                close()
                moveTo(12.0f, 15.75f)
                arcToRelative(3.75f, 3.75f, 0.0f, true, false, 0.0f, -7.5f)
                arcToRelative(3.75f, 3.75f, 0.0f, false, false, 0.0f, 7.5f)
                close()
            }
        }
        .build()
        return _settingSolid!!
    }

private var _settingSolid: ImageVector? = null

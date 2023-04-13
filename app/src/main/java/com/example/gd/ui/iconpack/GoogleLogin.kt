package com.example.gd.ui.iconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.example.gd.ui.IconPack

public val IconPack.GoogleLogin: ImageVector
    get() {
        if (_googleLogin != null) {
            return _googleLogin!!
        }
        _googleLogin = Builder(name = "GoogleLogin", defaultWidth = 46.0.dp, defaultHeight =
                46.0.dp, viewportWidth = 46.0f, viewportHeight = 46.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = SolidColor(Color(0x00000000)),
                    strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = EvenOdd) {
                moveTo(5.0f, 3.0f)
                lineTo(41.0f, 3.0f)
                arcTo(2.0f, 2.0f, 0.0f, false, true, 43.0f, 5.0f)
                lineTo(43.0f, 41.0f)
                arcTo(2.0f, 2.0f, 0.0f, false, true, 41.0f, 43.0f)
                lineTo(5.0f, 43.0f)
                arcTo(2.0f, 2.0f, 0.0f, false, true, 3.0f, 41.0f)
                lineTo(3.0f, 5.0f)
                arcTo(2.0f, 2.0f, 0.0f, false, true, 5.0f, 3.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF4285F4)), stroke = SolidColor(Color(0x00000000)),
                    strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = EvenOdd) {
                moveTo(31.64f, 23.2045f)
                curveTo(31.64f, 22.5664f, 31.5827f, 21.9527f, 31.4764f, 21.3636f)
                lineTo(23.0f, 21.3636f)
                lineTo(23.0f, 24.845f)
                lineTo(27.8436f, 24.845f)
                curveTo(27.635f, 25.97f, 27.0009f, 26.9232f, 26.0477f, 27.5614f)
                lineTo(26.0477f, 29.8195f)
                lineTo(28.9564f, 29.8195f)
                curveTo(30.6582f, 28.2527f, 31.64f, 25.9455f, 31.64f, 23.2045f)
                lineTo(31.64f, 23.2045f)
                close()
            }
            path(fill = SolidColor(Color(0xFF34A853)), stroke = SolidColor(Color(0x00000000)),
                    strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = EvenOdd) {
                moveTo(23.0f, 32.0f)
                curveTo(25.43f, 32.0f, 27.4673f, 31.1941f, 28.9564f, 29.8195f)
                lineTo(26.0477f, 27.5614f)
                curveTo(25.2418f, 28.1014f, 24.2109f, 28.4205f, 23.0f, 28.4205f)
                curveTo(20.6559f, 28.4205f, 18.6718f, 26.8373f, 17.9641f, 24.71f)
                lineTo(14.9573f, 24.71f)
                lineTo(14.9573f, 27.0418f)
                curveTo(16.4382f, 29.9832f, 19.4818f, 32.0f, 23.0f, 32.0f)
                lineTo(23.0f, 32.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFBBC05)), stroke = SolidColor(Color(0x00000000)),
                    strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = EvenOdd) {
                moveTo(17.9641f, 24.71f)
                curveTo(17.7841f, 24.17f, 17.6818f, 23.5932f, 17.6818f, 23.0f)
                curveTo(17.6818f, 22.4068f, 17.7841f, 21.83f, 17.9641f, 21.29f)
                lineTo(17.9641f, 18.9582f)
                lineTo(14.9573f, 18.9582f)
                curveTo(14.3477f, 20.1732f, 14.0f, 21.5477f, 14.0f, 23.0f)
                curveTo(14.0f, 24.4523f, 14.3477f, 25.8268f, 14.9573f, 27.0418f)
                lineTo(17.9641f, 24.71f)
                lineTo(17.9641f, 24.71f)
                close()
            }
            path(fill = SolidColor(Color(0xFFEA4335)), stroke = SolidColor(Color(0x00000000)),
                    strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = EvenOdd) {
                moveTo(23.0f, 17.5795f)
                curveTo(24.3214f, 17.5795f, 25.5077f, 18.0336f, 26.4405f, 18.9255f)
                lineTo(29.0218f, 16.3441f)
                curveTo(27.4632f, 14.8918f, 25.4259f, 14.0f, 23.0f, 14.0f)
                curveTo(19.4818f, 14.0f, 16.4382f, 16.0168f, 14.9573f, 18.9582f)
                lineTo(17.9641f, 21.29f)
                curveTo(18.6718f, 19.1627f, 20.6559f, 17.5795f, 23.0f, 17.5795f)
                lineTo(23.0f, 17.5795f)
                close()
            }
        }
        .build()
        return _googleLogin!!
    }

private var _googleLogin: ImageVector? = null

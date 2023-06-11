package com.dongminpark.projectgd.Effects

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.dongminpark.projectgd.R

@Composable
fun ProfileImage(ImageSize: Int, image: String = R.drawable.mainlogo_white.toString()) {
    val painter = // 이미지 로드 중 및 실패 시 표시할 이미지 리소스를 설정할 수 있습니다.
        rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = image).apply(block = fun ImageRequest.Builder.() {
                // 이미지 로드 중 및 실패 시 표시할 이미지 리소스를 설정할 수 있습니다.
                placeholder(R.drawable.mainlogo_white)
                error(R.drawable.mainlogo_white)
            }).build()
        )

    Image(
        painter = painter,
        contentScale = ContentScale.Fit,
        contentDescription = "Default Profile Picture",
        modifier = Modifier
            .size(ImageSize.dp)
            .clip(CircleShape)
            .border(1.dp, MaterialTheme.colors.secondaryVariant, CircleShape)
    )
}
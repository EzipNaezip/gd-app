package com.example.gd.Effects

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.gd.R

@Composable
fun ProfileImage(ImageSize: Int, Image: Int = R.drawable.logo) {
    if (profileImageUri == null) {
        Image(
            painter = painterResource(id = Image),
            contentScale = ContentScale.Fit,
            contentDescription = "Default Profile Picture",
            modifier = Modifier
                .size(ImageSize.dp)
                .clip(CircleShape)
                .border(1.dp, MaterialTheme.colors.secondaryVariant, CircleShape)
        )
    } else {
        Image(
            painter = rememberAsyncImagePainter(model = profileImageUri),
            contentScale = ContentScale.Crop,
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(ImageSize.dp)
                .clip(CircleShape)
                .border(1.dp, MaterialTheme.colors.secondaryVariant, CircleShape)
        )
    }
}
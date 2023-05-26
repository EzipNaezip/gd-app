package com.example.gd.Effects

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ImageFormat(image: Int){
    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(
                1.dp,
                MaterialTheme.colors.secondaryVariant,
                RoundedCornerShape(12.dp)
            )
    ){
        Image(
            contentScale = ContentScale.Fit,
            painter = painterResource(id = image),
            contentDescription = "Image",
            modifier = Modifier
                .size(180.dp)
                .clip(RoundedCornerShape(12.dp))
        )
    }
}
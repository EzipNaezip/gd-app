package com.example.gd.Button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gd.R

@Composable
fun FavoriteButton(){
    var isFavorite by rememberSaveable { mutableStateOf(false) }

    Icon(
        painter = if (isFavorite) painterResource(id = R.drawable.favorite_red) else painterResource(id = R.drawable.favorite_outline),
        contentDescription = "Favorite",
        modifier = Modifier
            .width(26.dp)
            .height(26.dp)
            .clickable {
                isFavorite = !isFavorite
                // favorite 추가 Api 호출
                // 클릭하면 imageVector 변경
            },
        tint = Color.Unspecified
    )
}
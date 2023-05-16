package com.example.gd.Button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gd.ui.IconPack
import com.example.gd.ui.iconpack.FavoriteOutline
import com.example.gd.ui.iconpack.FavoriteSolid

@Composable
fun FavoriteButton(){
    var isFavorite by rememberSaveable { mutableStateOf(false) }

    Icon(
        imageVector = if (isFavorite) IconPack.FavoriteSolid else IconPack.FavoriteOutline,
        contentDescription = "Favorite",
        modifier = Modifier
            .width(26.dp)
            .height(26.dp)
            .clickable {
                isFavorite = !isFavorite
                // favorite 추가 Api 호출
                // 클릭하면 imageVector 변경
            }
    )
}
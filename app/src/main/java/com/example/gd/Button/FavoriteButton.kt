package com.example.gd.Button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gd.ui.IconPack
import com.example.gd.ui.iconpack.FavoriteOutline

@Composable
fun FavoriteButton(){
    Icon(
        imageVector = IconPack.FavoriteOutline,
        contentDescription = "Favorite",
        modifier = Modifier
            .width(26.dp)
            .height(26.dp)
            .clickable {
                // favorite 추가 Api 호출
                // 클릭하면 imageVector 변경
            }
    )
}
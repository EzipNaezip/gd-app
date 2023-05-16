package com.example.gd.Button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gd.ui.IconPack
import com.example.gd.ui.iconpack.BookmarkOutline

@Composable
fun BookmarkButton(){
    Icon(
        imageVector = IconPack.BookmarkOutline,
        contentDescription = "Bookmark",
        modifier = Modifier
            .width(26.dp)
            .height(26.dp)
            .clickable {
                // 북마크 추가 Api 호출
                // 클릭하면 imageVector 변경
            }
    )
}
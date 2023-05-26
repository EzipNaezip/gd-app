package com.example.gd.Button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gd.ui.IconPack
import com.example.gd.ui.iconpack.BookmarkOutline
import com.example.gd.ui.iconpack.BookmarkSolid

@Composable
fun BookmarkButton(){
    var isBookmark by rememberSaveable {mutableStateOf(false)}

    Icon(
        imageVector = if (isBookmark) IconPack.BookmarkSolid else IconPack.BookmarkOutline,
        contentDescription = "Bookmark",
        modifier = Modifier
            .padding(8.dp)
            .size(26.dp)
            .clickable {
                isBookmark = !isBookmark
                // 북마크 추가 Api 호출
                // 클릭하면 imageVector 변경
            }
    )
}
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gd.R
import com.example.gd.ui.IconPack
import com.example.gd.ui.iconpack.BookmarkOutline
import com.example.gd.ui.iconpack.BookmarkSolid

@Composable
fun BookmarkButton(){
    var isBookmark by rememberSaveable {mutableStateOf(false)}

    Icon(
        painter = if (isBookmark) painterResource(id = R.drawable.bookmark_color) else painterResource(id = R.drawable.bookmark_outline),
        contentDescription = "Bookmark",
        modifier = Modifier
            .size(26.dp)
            .clickable {
                isBookmark = !isBookmark
                // 북마크 추가 Api 호출
                // 클릭하면 imageVector 변경
            },
        tint = Color.Unspecified
    )
}
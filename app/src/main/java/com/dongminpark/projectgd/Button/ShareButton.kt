package com.dongminpark.projectgd.Button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dongminpark.projectgd.ui.IconPack
import com.ezipnaezip.gd.ui.iconpack.Share

@Composable
fun ShareButton(){
    Icon(
        imageVector = IconPack.Share, // save버튼으로 수정 필요
        contentDescription = "Save",
        modifier = Modifier
            .width(26.dp)
            .height(26.dp)
            .clickable {
                // 북마크 추가 기능
            }
    )
}
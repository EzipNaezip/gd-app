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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gd.R

@Composable
fun SaveButton(){
    var isSaved by rememberSaveable { mutableStateOf(false) }

    Icon(
        painter = if (isSaved) painterResource(id = R.drawable.save) else painterResource(id = R.drawable.save_black),
        contentDescription = "Save",
        modifier = Modifier
            .width(26.dp)
            .height(26.dp)
            .clickable {
                isSaved = !isSaved
                // 북마크 추가 기능
            },
        tint = Color.Unspecified
    )
}
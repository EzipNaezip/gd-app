package com.example.gd.Button

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gd.ui.theme.suite

@Composable
fun FollowButton(){
    var isFollowed by rememberSaveable { mutableStateOf(false) }

    Button(
        onClick = {
            isFollowed = !isFollowed
        }
    ) {
        Text(
            text = if (isFollowed) "팔로우" else "팔로우 해제",
            fontFamily = suite,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 15.sp,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 2.dp)
        )
    }
}
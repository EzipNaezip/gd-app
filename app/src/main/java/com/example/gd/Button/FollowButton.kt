package com.example.gd.Button

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gd.ui.theme.suite

@Composable
fun FollowButton(){
    Button(onClick = { /*TODO*/ }) {
        Text(
            text = "Follow",
            fontFamily = suite,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 15.sp,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 2.dp)
        )
    }
}
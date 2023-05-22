package com.example.gd.Button

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gd.ui.theme.suite

@Composable
fun FollowButton() {
    var isFollowing by remember { mutableStateOf(true) }

    val buttonColors = if (isFollowing) {
        ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        )
    } else {
        ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primaryVariant
        )
    }

    OutlinedButton(
        onClick = { isFollowing = !isFollowing },
        colors = buttonColors,
        modifier = Modifier.size(80.dp, 40.dp),
        shape = RoundedCornerShape(30)
    ) {
        Text(
            text = if (isFollowing) "팔로잉" else "팔로우",
            color = if (isFollowing) Color.Black else Color.White,
            fontFamily = suite,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
        )
    }
}


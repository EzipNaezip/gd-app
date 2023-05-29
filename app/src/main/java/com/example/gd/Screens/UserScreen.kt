package com.example.gd.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gd.Button.FollowButton
import com.example.gd.Effects.UserMyContent
import com.example.gd.R
import com.example.gd.ui.IconPack
import com.example.gd.ui.iconpack.Left
import com.example.gd.ui.theme.suite

@SuppressLint("UnrememberedMutableState")
@Composable
fun UserScreen(navController: NavController, route: String) {
    val userInfo = Info("박동민", "0123456789012345678901234567890123456789", 120, 200, false)
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            imageVector = IconPack.Left,
            contentDescription = "Back",
            modifier = Modifier
                .clickable {
                    navController.popBackStack()
                }
                .width(30.dp)
                .height(30.dp)
        )

        UserMyContent(navController = navController, route = route, userInfo)
    }
}
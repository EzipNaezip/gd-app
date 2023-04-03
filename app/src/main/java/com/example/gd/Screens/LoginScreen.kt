package com.example.gd

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gd.navigation.BottomScreen
import com.example.gd.navigation.MainScreenView
import com.example.gd.ui.theme.suite
import com.example.splashscreen.navigation.Screen

@Composable
fun LoginScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "로그인 페이지 구현 예정",
            fontFamily = suite,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp,
            color = Color.Black,
            modifier = Modifier.padding(50.dp)
        )
        Text(
            text = "ExtraBold / 엑스트라 볼드",
            fontFamily = suite,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black,
        )
        Text(
            text = "Bold / 볼드",
            fontFamily = suite,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
        )
        Text(
            text = "SemiBold / 세미볼드",
            fontFamily = suite,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
        )
        Text(
            text = "Normal / 노말",
            fontFamily = suite,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
        )
        Text(
            text = "Medium / 미디움",
            fontFamily = suite,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
        )
        Text(
            text = "Light / 라이트",
            fontFamily = suite,
            fontWeight = FontWeight.Light,
            color = Color.Black,
        )
        Button(onClick = { navController.navigate(Screen.Once.route) }) {
            Text(text = "로딩창으로 이동", color = Color.Black)
        }
    }
}
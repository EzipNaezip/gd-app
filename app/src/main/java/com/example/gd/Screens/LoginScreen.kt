package com.example.gd

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gd.ui.IconPack
import com.example.gd.ui.iconpack.GoogleLogin
import com.example.gd.ui.iconpack.KakaoLogin
import com.example.gd.ui.theme.suite
import com.example.splashscreen.navigation.Screen

@Composable
fun LoginScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = "Musinsa에서\n원하는 집을 마음껏 상상해봐",
            fontFamily = suite,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 100.dp)
        )
        Spacer(modifier = Modifier.height(300.dp))
        Text(
            text = "SNS계정으로 로그인하기",
            fontFamily = suite,
            fontWeight = FontWeight.Light,
            color = Color.Black,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row() {
            Image(
                imageVector = IconPack.GoogleLogin,
                contentDescription = "Google Login",
                modifier = Modifier
                    .clickable {
                        navController.navigate(Screen.Once.route)
                        // 구글 OAuth 사용 코드로 변경 예정
                    }
                    .size(50.dp)
                    .border(1.dp, Color.LightGray)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Image(
                imageVector = IconPack.KakaoLogin,
                contentDescription = "Kakao Login",
                modifier = Modifier
                    .clickable{

                    }
                    .size(50.dp)
            )
        }
    }
}
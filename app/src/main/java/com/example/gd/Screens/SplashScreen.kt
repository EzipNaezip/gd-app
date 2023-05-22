package com.example.gd

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gd.Screens.OAuthData
import com.example.gd.navigation.Screen
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SplashScreen(navController: NavHostController) {
    var test by remember { mutableStateOf(true) } // 함수가 두번 호출돼서 화면 깜빡이는 것 방지

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background) // 로딩창 배경생
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier.padding(vertical = 60.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = "GD Service",
                    color = Color.Black,
                    fontSize = 15.sp
                )
            }
        }

        // 현재는 Delay를 주고 특정 시간 뒤 이동하는 방식.
        // 추후에는 로그인 되었는지 확인하고, 확인되면 이동하는 방식으로 변경 예정
        if (test) {
            Handler(Looper.getMainLooper()).postDelayed({
                test = false
                if (OAuthData.account == null) {
                    // 로그인 안돼있음
                    navController.navigate(Screen.Login.route)
                } else {
                    // 로그인 돼있음
                    navController.navigate(Screen.Once.route)
                }
            }, 1000)
        }
    }
}
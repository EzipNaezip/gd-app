package com.dongminpark.projectgd.Screens

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dongminpark.projectgd.App
import com.dongminpark.projectgd.R
import com.dongminpark.projectgd.Retrofit.RetrofitManager
import com.dongminpark.projectgdn.navigation.Screen
import com.dongminpark.projectgd.Utils.Constants
import com.dongminpark.projectgd.Utils.MESSAGE
import com.dongminpark.projectgd.Utils.RESPONSE_STATE


@Composable
fun SplashScreen(navController: NavController) {
    navController.enableOnBackPressed(false)
    var test by remember { mutableStateOf(true) } // 함수가 두번 호출돼서 화면 깜빡이는 것 방지

    // 로그인
    if (test) {
        Handler(Looper.getMainLooper()).postDelayed({
            test = false
            loginStep(navController)
        }, 1000)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background), // 로딩창 배경생,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.mainlogo_white),
            contentDescription = "logo",
            modifier = Modifier
                .size(200.dp)
        )
    }
}

fun loginStep(navController: NavController) {
    if (OAuthData.account == null) {
        // 로그인 안돼있음
        navController.navigate(Screen.Login.route)
    } else {
        // 로그인 돼있음
        val uid = OAuthData.auth?.currentUser?.uid ?: ""
        RetrofitManager.instance.firebaseConnect(uid, completion = { responseState ->

            when (responseState) {
                RESPONSE_STATE.OKAY -> {
                    OAuthData.nav?.navigate(Screen.Once.route)
                    Log.d(Constants.TAG, "api 호출 성공")
                }
                RESPONSE_STATE.FAIL -> {
                    Toast.makeText(App.instance, MESSAGE.ERROR, Toast.LENGTH_SHORT).show()
                    Log.d(Constants.TAG, "api 호출 에러")
                }
            }
        })
    }
}
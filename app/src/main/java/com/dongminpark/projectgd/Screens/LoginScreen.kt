package com.dongminpark.projectgd.Screens

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.dongminpark.projectgd.App
import com.dongminpark.projectgd.R
import com.dongminpark.projectgd.Retrofit.RetrofitManager
import com.dongminpark.projectgd.ui.theme.suite
import com.dongminpark.projectgdn.navigation.Screen
import com.dongminpark.projectgd.Utils.Constants.TAG
import com.dongminpark.projectgd.Utils.MESSAGE
import com.dongminpark.projectgd.Utils.RESPONSE_STATE
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GoogleAuthProvider

var isLoginLoading by mutableStateOf(false)

@Composable
fun LoginScreen(navController: NavHostController) {
    navController.enableOnBackPressed(false)
    var counts by rememberSaveable { mutableStateOf(0) }

    if (isLoginLoading){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Loading...",
                fontFamily = suite,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp,
                color = Color.Black,
                modifier = Modifier
                    .padding(vertical = 100.dp)
            )
        }
    }else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            // 이스터에그
            if (counts > 13) {
                    Dialog(
                        onDismissRequest = {
                            counts = 0
                        }
                    ) {
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Button(onClick = {
                                counts = 0
                            }) {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    textFormat(
                                        str = "\n\n\n경기대학교 컴퓨터공학부 \n2023-1 기초캡스톤팀 Man-of-Steel\n\nInfra 팀\n김관식(팀장) / 한종걸 / 이찬영\n\nAndroid 팀\n박동민(팀장) / 정진서\n\nWeb FE 팀\n신현호(팀장) / 김상후 / 이태용\n\nBE팀\n한관희(팀장) / 이영학 / 김성준 / 김혜빈 / 김도희\n\n\n "
                                    )
                                }
                            }
                        }
                    }
            }

            Text(
                text = "이집내집에서\n원하는 집을 마음껏 상상해봐",
                fontFamily = suite,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 25.sp,
                color = Color.Black,
                modifier = Modifier
                    .padding(vertical = 100.dp, horizontal = 0.dp)
                    .clickable {
                        counts++
                    }
            )

            // 비율 맞추기용도
            Spacer(modifier = Modifier)

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "SNS계정으로 로그인하기",
                    fontFamily = suite,
                    fontWeight = FontWeight.Light,
                    color = Color.Black,
                )

                Icon(
                    painter = painterResource(id = R.drawable.google_kor),
                    contentDescription = "Google Login",
                    modifier = Modifier
                        .clickable {
                            isLoginLoading = true
                            googleLogin()
                        }
                        .padding(10.dp)
                        .width(200.dp)
                        .border(1.dp, Color.LightGray),
                    tint = Color.Unspecified
                )
            }
        }
    }
}

fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
    Log.e("Firebase", "진입 성공")
    var credntial = GoogleAuthProvider.getCredential(account?.idToken, null)

    OAuthData.auth?.signInWithCredential(credntial)
        ?.addOnCompleteListener { task ->
            if (task.isSuccessful){
                var uid = OAuthData.auth?.currentUser?.uid.toString()

                Log.e("Firebase Success", "네 성공했습니다.")
                Log.e("uid", uid) // BE가 보내달라함

                RetrofitManager.instance.firebaseConnect(uid, completion = {
                        responseState ->
                    when (responseState) {
                        RESPONSE_STATE.OKAY -> {
                            OAuthData.nav?.navigate(Screen.Once.route)
                            Log.d(TAG, "api 호출 성공")
                        }
                        RESPONSE_STATE.FAIL -> {
                            isLoginLoading = false
                            Toast.makeText(App.instance, MESSAGE.ERROR, Toast.LENGTH_SHORT).show()
                            Log.d(TAG, "api 호출 에러")
                        }
                    }
                })
            }
            else{
                isLoginLoading = false
                Toast.makeText(App.instance, MESSAGE.ERROR, Toast.LENGTH_SHORT).show()
                Log.e("에러 : ", "${task.exception}")
                Log.e("Firebase ERROR", "먼가 먼가 잘못됨")
            }
        }
}

fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
    try {
        val account = completedTask.getResult(ApiException::class.java)
        val serviceAccountKey = account.serverAuthCode.toString()
        val email = account?.email.toString()
        val name = account?.displayName.toString()
        val profileImg = account?.photoUrl
        var userId = account?.id.toString()
        var accessToken = account?.idToken.toString()

        Log.e("Google account email", email)
        Log.e("Google account name", name)
        Log.e("Google account profileImg", "$profileImg")
        Log.e("Google account userId", userId)
        Log.e("Google account serviceAccountKey", serviceAccountKey)
        Log.e("Google account accessId", accessToken)

        firebaseAuthWithGoogle(account)

    } catch (e: ApiException) {
        isLoginLoading = false
        Log.e("Google account", "signInResult:failed Code = " + e.statusCode)
    }
}

fun googleLogin() {
    var signIntent: Intent = OAuthData.mGoogleSignInClient!!.getSignInIntent()
    OAuthData.GoogleSignResultLauncher.launch(signIntent)
}
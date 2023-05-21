package com.example.gd

import android.content.Intent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gd.Screens.OAuthData
import com.example.gd.ui.IconPack
import com.example.gd.ui.iconpack.GoogleLogin
import com.example.gd.ui.iconpack.KakaoLogin
import com.example.gd.ui.theme.suite
import com.example.gd.navigation.Screen
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GoogleAuthProvider

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
            text = "내일의 집에서\n원하는 집을 마음껏 상상해봐",
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

        Row {
            Image(
                imageVector = IconPack.GoogleLogin,
                contentDescription = "Google Login",
                modifier = Modifier
                    .clickable {
                        googleLogin()
                    }
                    .size(50.dp)
                    .border(1.dp, Color.LightGray)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Image(
                imageVector = IconPack.KakaoLogin,
                contentDescription = "Kakao Login",
                modifier = Modifier
                    .clickable {
                        navController.navigate(Screen.Once.route)
                        // 구글 OAuth 사용 코드로 변경 예정
                    }
                    .size(50.dp)
            )
        }
    }
}

fun firebaseAuthWithGoogle(accountt : GoogleSignInAccount?){
    Log.e("Firebase", "진입 성공")
    var credntial = GoogleAuthProvider.getCredential(accountt?.idToken, null)
    OAuthData.auth?.signInWithCredential(credntial)
        ?.addOnCompleteListener {task ->
            if (task.isSuccessful) Log.e("Firebase Success", "네 성공했습니다.")
            else Log.e("Firebase ERROR", "먼가 먼가 잘못됨")
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

        //var googletoken = account?.idToken.toString()
        //var googletokenAuth = account?.serverAuthCode.toString()

        firebaseAuthWithGoogle(account)

        Log.e("Google account email", email)
        Log.e("Google account name", name)
        Log.e("Google account profileImg", "$profileImg")
        Log.e("Google account userId", userId)
        Log.e("Google account serviceAccountKey", serviceAccountKey)

        Log.e("uid", OAuthData.auth?.uid.toString()) // BE가 보내달라함
        // 위에 정보 4개 BE로 넘기면 됨. 구글 로그인 버튼 누를때 마다 넘기기
        // 일단은 로그인 성공하면 "정보입력Screen"으로 보냈음.
        OAuthData.nav!!.navigate(Screen.EnterMember.route)
        // 위 정보들 BE로 보냈을때 이미 있는 정보면 "정보입력Screen"이 아니라 메인으로 보내야함
        //OAuthData.nav!!.navigate(Screen.Once.route)

    } catch (e: ApiException) {
        Log.e("Google account", "signInResult:failed Code = " + e.statusCode)
    }
}

fun googleLogin(){
    var signIntent: Intent = OAuthData.mGoogleSignInClient!!.getSignInIntent()
    OAuthData.GoogleSignResultLauncher.launch(signIntent)
}
package com.dongminpark.projectgd.Screens

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class OAuthData {
    companion object {
        val ID = "164566727064-ldq29hf1m3klv9rb3eukt53d9qdfn7jp.apps.googleusercontent.com"
        lateinit var GoogleSignResultLauncher: ActivityResultLauncher<Intent>
        var auth : FirebaseAuth? = null
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(ID)
            .requestServerAuthCode(ID)
            .requestProfile()
            .requestEmail()
            .requestId()
            .build()
        var mGoogleSignInClient: GoogleSignInClient? = null
        var account : GoogleSignInAccount? = null
        var nav : NavHostController? = null

        // 임시로 amdin token 사용. 추후 변경 예정.
        var ACCESS_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0b2tlbiIsImZvbGxvd0NvdW50IjoiMCIsInJvbGUiOiJBRE1JTiIsInByb3ZpZGVyIjoiR09PR0xFIiwicHJvZmlsZUltZ1VybCI6Imh0dHA6Ly9leGFtcGxlLmNvbS9wcm9maWxlLnBuZyIsIm5hbWUiOiJbMSA6IGFkbWluXSIsImRlc2NyaXB0aW9uIjoiVGhpcyBpcyB0aGUgYWRtaW4gdXNlciIsInBvc3RDb3VudCI6IjAiLCJ1c2VySWQiOiIxIiwiZm9sbG93ZXJDb3VudCI6IjAiLCJlbWFpbCI6ImFkbWluQGV4YW1wbGUuY29tIiwiaWF0IjoxNjg1NTMzMDY4LCJleHAiOjE3MTcxNTU0Njh9.rXmhh-RDdnXszwUKa-G7TsuB1zoa1M7CABwUwY8VxOU"
    }
}
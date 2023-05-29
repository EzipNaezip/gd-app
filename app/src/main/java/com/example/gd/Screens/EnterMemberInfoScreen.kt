package com.example.gd.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.gd.Effects.ProfileTextContent
import com.example.gd.Effects.ProfileTextScreen

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun EnterMemberInfoScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        ProfileTextScreen(
            navController = navController,
            titleText = "프로필 등록",
            content = {
                ProfileTextContent(buttonText = "프로필 사진 등록")
            },
            popupTitleText = "사용자 프로필 저장",
            confirmDialogText = "프로필을 설정하시겠습니까?\n추후 수정이 가능합니다.",
            completeDialogText = "프로필 설정이 완료되었습니다.",
            isLeftButton = false
        )
    }
}


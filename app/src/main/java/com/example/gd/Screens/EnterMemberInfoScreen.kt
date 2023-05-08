package com.example.gd.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gd.Effects.ConfirmDismissPopupFormat
import com.example.gd.Effects.TextFieldFormat
import com.example.gd.Effects.TopAppBarScreenFormat
import com.example.gd.R
import com.example.gd.ui.theme.suite
import com.example.splashscreen.navigation.Screen
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun EnterMemberInfoScreen(navController: NavHostController) {
    var popup by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        TopAppBarScreenFormat(
            titleText = "프로필 등록",
            IsLeftButton = false,
            IsRightButton = true,
            content = { EnterMemberInfoContent() },
            leftButtonClick = { },
            rightButtonClick = { popup = true }
        )
        if (popup) {
            var profileEditConfirmPopup by remember { mutableStateOf(true) }
            var profileEditCompletePopup by remember { mutableStateOf(false) }

            if (profileEditConfirmPopup) {
                ConfirmDismissPopupFormat(
                    titleText = "사용자 프로필 저장",
                    dialogText = "프로필을 설정하시겠습니까?\n추후 수정이 가능합니다.",
                    buttonText = "완료",
                    buttonColor = MaterialTheme.colors.primaryVariant,
                    runButtonClick = {
                        profileEditConfirmPopup = false
                        profileEditCompletePopup = true
                    },
                    dismissButtonClick = { //취소
                        profileEditConfirmPopup = false
                        popup = false
                    },
                    ifDoubleButton = true
                )
            }
            if (profileEditCompletePopup) {
                ConfirmDismissPopupFormat(
                    titleText = "사용자 프로필 저장",
                    dialogText = "프로필 설정이 완료되었습니다.",
                    buttonText = "확인",
                    buttonColor = MaterialTheme.colors.onPrimary,
                    runButtonClick = {
                        profileEditCompletePopup = false
                        settingScreen = "default"
                        navController.navigate(Screen.Once.route)
                        // *** 프로필 저장 기능 코드 작성 ***
                    },
                    dismissButtonClick = {},
                    ifDoubleButton = false
                )
            }
        }
    }
}

@Composable
fun EnterMemberInfoContent() {
    Column( //페이지 내용
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .padding(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(1.dp, MaterialTheme.colors.secondaryVariant, CircleShape)
            )
        }
        Button(
            onClick = { /* 사진 변경 기능 실행 */ },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 4.dp),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp
            ),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
        ) {
            Text(
                text = "프로필 사진 등록",
                fontFamily = suite,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = MaterialTheme.colors.primaryVariant
            )
        }
        Column {
            TextFieldFormat("이름")
            TextFieldFormat("사용자 아이디") //인스타의 @sample_test
            TextFieldFormat("소개")
            TextFieldFormat("링크")
        }
    }
}
package com.example.gd.Effects

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.gd.Screens.profileEditScreen
import com.example.gd.Screens.settingScreen
import com.example.gd.navigation.Screen

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ProfileTextScreen(
    navController: NavHostController,
    titleText: String,
    content: @Composable () -> Unit,
    popupTitleText: String,
    confirmDialogText: String,
    completeDialogText: String
) {
    var popup by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        TopAppBarScreenFormat(
            titleText = titleText,
            IsLeftButton = false,
            IsRightButton = true,
            content = content,
            leftButtonClick = { },
            rightButtonClick = { popup = true }
        )
        if (popup) {
            var profileEditConfirmPopup by remember { mutableStateOf(true) }
            var profileEditCompletePopup by remember { mutableStateOf(false) }

            if (profileEditConfirmPopup) {
                ConfirmDismissPopupFormat(
                    titleText = popupTitleText,
                    dialogText = confirmDialogText,
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
                    titleText = popupTitleText,
                    dialogText = completeDialogText,
                    buttonText = "확인",
                    buttonColor = MaterialTheme.colors.onPrimary,
                    runButtonClick = {
                        profileEditCompletePopup = false
                        if (titleText == "프로필 등록") {
                            navController.navigate(Screen.Once.route)
                        } else if (titleText == "프로필 수정"){
                            profileEditScreen = "EditScreenOpen"
                        }
                    },
                    dismissButtonClick = {},
                    ifDoubleButton = false
                )
            }
        }
    }
}
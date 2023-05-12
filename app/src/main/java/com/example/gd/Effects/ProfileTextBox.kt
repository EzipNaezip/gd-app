package com.example.gd.Effects

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.gd.R
import com.example.gd.navigation.Screen
import com.example.gd.ui.theme.suite

var profileEditScreenState by mutableStateOf("default")
//뒤로가기 시 초기화를 위한 전역변수
var editIsOpen by mutableStateOf("default")
//코루틴과 sheetState 전달을 위한 전역변수
var profileImageUri by mutableStateOf<Uri?>(null)

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ProfileTextScreen(
    navController: NavController,
    titleText: String,
    content: @Composable () -> Unit,
    popupTitleText: String,
    confirmDialogText: String,
    completeDialogText: String,
) {
    var popup by remember { mutableStateOf(false) }

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
                    } else if (titleText == "프로필 수정") {
                        editIsOpen = "on"
                    }
                },
                dismissButtonClick = { },
                ifDoubleButton = false
            )
        }
    }
}

@SuppressLint("IntentReset")
@Composable
fun ProfileTextContent(buttonText: String) {
    val activityResultLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri -> if (uri != null) { profileImageUri = uri } }

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
            ProfileImage(100)
        }
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                intent.type = "image/*"
                val mimeTypes = arrayOf("image/jpeg", "image/png")
                activityResultLauncher.launch(mimeTypes)
            },
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
                text = buttonText,
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
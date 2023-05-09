package com.example.gd.Screens

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
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.gd.Effects.ProfileTextScreen
import com.example.gd.Effects.TextFieldFormat
import com.example.gd.R
import com.example.gd.navigation.Screen
import com.example.gd.ui.theme.suite

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun EnterMemberInfoScreen(navController: NavHostController) {
    ProfileTextScreen(
        navController = navController,
        titleText = "프로필 등록",
        content = { EnterMemberInfoContent() },
        popupTitleText = "사용자 프로필 저장",
        confirmDialogText = "프로필을 설정하시겠습니까?\n추후 수정이 가능합니다.",
        completeDialogText = "프로필 설정이 완료되었습니다."
    )
}

@SuppressLint("IntentReset")
@Composable
fun EnterMemberInfoContent() {
    var imageUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    val activityResultLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri -> if (uri != null) { imageUri = uri } }

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
            if (imageUri == null) {
                Image(
                    painterResource(id = R.drawable.logo),
                    contentScale = ContentScale.Fit,
                    contentDescription = "Default Profile Picture",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(1.dp, MaterialTheme.colors.secondaryVariant, CircleShape)
                )
            } else {
                Image(
                    painter = rememberAsyncImagePainter(model = imageUri),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(1.dp, MaterialTheme.colors.secondaryVariant, CircleShape)
                )
            }
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
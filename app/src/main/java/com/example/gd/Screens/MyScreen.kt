package com.example.gd.Screens

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gd.Button.ProfileEditButton
import com.example.gd.Effects.*
import com.example.gd.ui.IconPack
import com.example.gd.ui.iconpack.Right
import com.example.gd.ui.theme.suite
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableState")
@Composable
fun MyScreen(navController: NavController) {
    val userInfo = Info("박동민", "0123456789012345678901234567890123456789", 12345678, 234567, true)
    UserMyContent(navController, "my", userInfo)
}


@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ProfileEditScreen(
    sheetState: ModalBottomSheetState,
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    var screen by remember { mutableStateOf(true) }

    if (screen) {
        ModalBottomSheetLayout(
            sheetState = sheetState,
            sheetContent = {
                coroutineScope.launch {
                    sheetState.animateTo(ModalBottomSheetValue.Expanded)
                }
                ProfileTextScreen(
                    navController = navController,
                    titleText = "프로필 수정",
                    content = {
                        ProfileTextContent(buttonText = "프로필 사진 수정")
                    },
                    popupTitleText = "변경 사항 저장",
                    confirmDialogText = "프로필 변경 사항을 저장하시겠습니까?",
                    completeDialogText = "프로필 변경 사항이 저장되었습니다.",
                    isLeftButton = true
                )
                if (editIsOpen == "on") {
                    coroutineScope.launch {
                        sheetState.hide()
                        profileEditScreenState = "default"
                        editIsOpen = "default"
                    }
                }
            }
        ) {}
    }
}

@Composable
fun TextFormat(text1: String, text2: String) {
    Box(
        modifier = Modifier
            .width(90.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = text1,
                fontFamily = suite,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                text = text2,
                fontFamily = suite,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Composable
fun ButtonFormat(text1: String, text2: String, navController: NavController, route: String) {
    Box(
        modifier = Modifier
            .width(90.dp)
            .clickable {
                when (text2) {
                    "Following" -> navController.navigate("${route}_follow_screen/FOLLOWING")
                    "Follower" -> navController.navigate("${route}_follow_screen/FOLLOWER")
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = text1,
                fontFamily = suite,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                text = text2,
                fontFamily = suite,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

class Info(
    val name: String, var readme: String, var following: Int, var follower: Int, var isMe: Boolean
)

@Composable
fun WidthDivide() {
    Spacer(modifier = Modifier.padding(vertical = 5.dp))
    Divider(
        color = MaterialTheme.colors.secondaryVariant,
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
    )
    Spacer(modifier = Modifier.padding(vertical = 5.dp))
}

@Composable
fun ListView(
    name: String, bookMarkList: ArrayList<Product>,
    navController: NavController, route: String, is_me: Boolean = true
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(horizontal = 10.dp)
    ) {
        Row {
            Text(
                text = "$name",
                fontFamily = suite,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = MaterialTheme.colors.onPrimary
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    imageVector = IconPack.Right,
                    contentDescription = "View All",
                    modifier = Modifier
                        .clickable {
                            // 전체보기 페이지로 이동
                        }
                        .width(25.dp)
                        .height(25.dp)
                )
            }
        }
        addProduct(bookMarkList)

        LazyRow {
            items(bookMarkList) { item ->
                productFrame(item, navController, route, is_me)// 수정필요
            }
        }
    }
}
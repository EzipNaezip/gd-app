package com.example.gd.Screens

import android.annotation.SuppressLint
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
import com.example.gd.Effects.*
import com.example.gd.ui.IconPack
import com.example.gd.ui.iconpack.Right
import com.example.gd.ui.theme.suite
import kotlinx.coroutines.launch

var myScreenButtonIndex by mutableStateOf(0)

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun MyScreen(navController: NavController) {
    val productList = arrayListOf<Product>()
    val userInfo = Info("박동민", "0123456789012345678901234567890123456789", 120, 200)

    var buttons by remember { mutableStateOf(listOf("갤러리", "북마크")) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                ) {
                    ProfileImage(100)
                }
                Text(
                    text = "${userInfo.name}",
                    fontFamily = suite,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = "${userInfo.readme}",
                    fontFamily = suite,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .padding(start = 30.dp, end = 30.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .padding(top = 8.dp)
                        .padding(start = 25.dp, end = 25.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    TextFormat("2", "Gallery")
                    TextFormat(userInfo.following.toString(), "Following")
                    TextFormat(userInfo.follower.toString(), "Follower")
                }

                // 프로필 수정 버튼
                OutlinedButton(
                    modifier = Modifier
                        .padding(start = 30.dp, end = 30.dp)
                        .fillMaxWidth(),
                    onClick = { profileEditScreenState = "프로필" },
                    border = BorderStroke(1.dp, MaterialTheme.colors.secondaryVariant)
                ) {
                    Text(
                        text = "프로필 수정",
                        fontFamily = suite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        color = MaterialTheme.colors.onPrimary
                    )
                }

                // 가로선
                WidthDivide()
            }

            //ALL, 갤러리, 북마크 버튼
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp, bottom = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                item {
                    buttons.forEachIndexed { index, label ->
                        OutlinedButton(
                            modifier = Modifier.size(145.dp, 35.dp),
                            onClick = {
                                myScreenButtonIndex = index
                                when (label) { // 버튼 클릭시 기능 실행
                                    buttons[0] -> println("${buttons[0]}")
                                    buttons[1] -> println("${buttons[1]}")
                                }
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = if (myScreenButtonIndex == index)
                                MaterialTheme.colors.primaryVariant else Color.White),
                            shape = RoundedCornerShape(30),
                            content = {
                                Text(
                                    text = label,
                                    fontFamily = suite,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp,
                                    color = if (myScreenButtonIndex == index) Color.White else Color.Black
                                )
                            }
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }
            }
        }

        addProduct(productList)
        val rows = productList.chunked(2)

        items(rows) { rowItems ->
            Row(Modifier.fillMaxWidth()) {
                rowItems.forEach { product ->
                    Column(Modifier.weight(1f)) {
                        productFrame(product, navController, "my")
                    }
                }
            }
        }
    }
    if (profileEditScreenState == "프로필") {
        ProfileEditScreen(
            sheetState = rememberModalBottomSheetState(
                initialValue = ModalBottomSheetValue.Hidden,
                confirmStateChange = { false } // 드래그 방지
            ),
            navController = navController
        )
    }
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
            .width(90.dp)
            .clickable {
                // Handle the click event here
            }
        ,
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
    val name: String, var readme: String, var follower: Int, var following: Int
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
    name: String, productList: ArrayList<Product>,
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
        addProduct(productList)

        LazyRow {
            items(productList) { item ->
                productFrame(item, navController, route, is_me)// 수정필요
            }
        }
    }
}
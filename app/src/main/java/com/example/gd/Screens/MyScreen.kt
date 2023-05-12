package com.example.gd.Screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import com.example.gd.Effects.*
import com.example.gd.R
import com.example.gd.ui.IconPack
import com.example.gd.ui.iconpack.Right
import com.example.gd.ui.theme.suite
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyScreen(navController: NavController) {
    val USER: Info
    val productList = arrayListOf<Product>()

    USER = Info(
        "박동민", "안녕하세요 저는 박동민입니다.\n 테스트 Readme입니다.",
        "pdm001125", 120, 200, R.drawable.logo
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            // 프로필 사진
            ProfileImage(80)

            // 유저 정보
            Column(modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)) {
                // user name
                Text(
                    text = USER.name,
                    fontFamily = suite,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.onPrimary,
                )
                Spacer(modifier = Modifier.padding(vertical = 2.dp))
                // user name
                Text(
                    text = "@${USER.ID}",
                    fontFamily = suite,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 10.sp,
                    color = MaterialTheme.colors.onPrimary,
                )

                // 팔로워, 팔로잉 수
                Row(modifier = Modifier.padding(vertical = 8.dp)) {
                    Text(
                        text = "팔로워  " + USER.follower.toString(),
                        fontFamily = suite,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
                        color = MaterialTheme.colors.onPrimary
                    )
                    Spacer(modifier = Modifier.padding(horizontal = 3.dp))
                    Divider(
                        color = MaterialTheme.colors.secondaryVariant,
                        modifier = Modifier
                            .height(10.dp)
                            .width(1.dp)
                    )
                    Spacer(modifier = Modifier.padding(horizontal = 3.dp))
                    Text(
                        text = "팔로잉  " + USER.following.toString(),
                        fontFamily = suite,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
                        color = MaterialTheme.colors.onPrimary
                    )
                }
            }
            if (true) {
                // 프로필 수정 버튼
                Box(modifier = Modifier.fillMaxWidth(), Alignment.BottomEnd) {
                    Button(onClick = {
                        profileEditScreenState = "프로필"
                    }) {
                        Text(text = "프로필 수정")
                    }
                }
            } else {
                // Follow & Unfollow Button
                Box(modifier = Modifier.fillMaxWidth(), Alignment.BottomEnd) {
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Follow")
                    }
                }
            }
        }
        // 가로선

        // Readme
        Text(
            text = "${USER.readme}",
            fontFamily = suite,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        WidthDivide()

        ListView("갤러리", productList, navController, "my",false)

        WidthDivide()

        ListView("북마크", productList, navController, "my")

        // 버튼(좋아요모음        >)
        // LazyRow(컨텐츠 표시)
    }

    when (profileEditScreenState) {
        "프로필" -> {
            ProfileEditScreen(
                sheetState = rememberModalBottomSheetState(
                    initialValue = ModalBottomSheetValue.Hidden,
                    confirmStateChange = { false } // 드래그 방지
                ),
                navController = navController
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ProfileEditScreen(
    sheetState: ModalBottomSheetState,
    navController: NavHostController
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
                    completeDialogText = "프로필 변경 사항이 저장되었습니다."
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

class Info(
    val name: String, val readme: String, val ID: String,
    var follower: Int, var following: Int,
    @DrawableRes val profilePicture: Int,
)

@Composable
fun WidthDivide(){
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
    navController: NavController, route: String, isMine: Boolean = true
){
    Column(modifier = Modifier
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
            items(productList) {item ->
                productFrame(item, navController, route, isMine)// 수정필요
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ProfileEditScreen(sheetState: ModalBottomSheetState, navController: NavController) {
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            coroutineScope.launch {
                sheetState.animateTo(ModalBottomSheetValue.Expanded)
            }
            ProfileTextScreen(
                navController = navController,
                titleText = "프로필 수정",
                content = { ProfileEditContent() },
                popupTitleText = "변경 사항 저장",
                confirmDialogText = "프로필 변경 사항을 저장하시겠습니까?",
                completeDialogText = "프로필 변경 사항이 저장되었습니다."
            )
        },
    ) {}
}

@Composable
fun ProfileEditContent() {
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
                text = "프로필 사진 변경",
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
package com.example.gd.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gd.R
import com.example.gd.ui.theme.suite
import kotlinx.coroutines.launch


var settingScreen by mutableStateOf("default")

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingScreen( //설정 화면
    navController: NavHostController,
    names: List<String> = listOf("프로필 수정", "회원 탈퇴", "로그아웃", "정보")
) {
    Column(modifier = Modifier.fillMaxHeight()) {
        //TopAppBarFormat("설정")

        for (name in names) { //설정 목록 버튼 4개 생성
            Surface(
                modifier = Modifier.background(colors.primary)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = {
                            when (name) {
                                "프로필 수정" -> { settingScreen = "프로필 수정" }
                                "회원 탈퇴" -> { settingScreen = "회원 탈퇴" }
                                "로그아웃" -> { settingScreen = "로그아웃" }
                                "정보" -> { settingScreen = "정보" }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = name,
                            fontFamily = suite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(vertical = 20.dp)
                                .padding(horizontal = 10.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
    when (settingScreen) {
        "프로필 수정" -> {
            ProfileEditScreen(
                sheetState = rememberModalBottomSheetState(
                    initialValue = ModalBottomSheetValue.Hidden,
                    confirmStateChange = { false } // 드래그 방지
                )
            )
        }
        "회원 탈퇴" -> {

        }
        "로그아웃" -> {
            LogoutPopupScreen()
        }
        "정보" -> {
            AppInfoScreen(navController)
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@Preview
fun TopAppBarFormat() {
    Scaffold(
        topBar = {
            TopAppBar(
                contentColor = colors.onPrimary,
                backgroundColor = colors.primary,
                title = {
                    Text(
                        text = "titleText",
                        fontFamily = suite,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back Button",
                            tint = colors.onPrimary
                        )
                    }
                },
                elevation = 8.dp,
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "Save Button",
                            tint = colors.primaryVariant
                        )
                    }
                }
            )
        }
    ) {
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
                        .border(1.dp, Color.LightGray, CircleShape)
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
                colors = ButtonDefaults.buttonColors(backgroundColor = Transparent)
            ) {
                Text(
                    text = "프로필 사진 변경",
                    fontFamily = suite,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = Color.Blue
                )
            }
            Column {
                TextFieldFormat("이름")
                TextFieldFormat("사용자 아이디") //인스타의 @sample_test
                TextFieldFormat("소개")
                TextFieldFormat("링크")
                // + 나이, 성별
            }
        }
    }
}

@Composable
fun TextFieldFormat(fieldTitle: String) {
    var userValue by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = fieldTitle,
            fontFamily = suite,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            color = Color.Gray
        )

        BasicTextField(
            value = userValue,
            onValueChange = { userValue = it },
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.primary)
                .padding(8.dp),
            textStyle = TextStyle(
                fontFamily = suite,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = colors.onPrimary,
                textAlign = TextAlign.Start
            ),
        )
    }
}

@Composable
fun TextFieldFormat() {}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileEditScreen(sheetState: ModalBottomSheetState) {
    val coroutineScope = rememberCoroutineScope()
    val roundedCornerRadius = 12.dp

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(
            topStart = roundedCornerRadius,
            topEnd = roundedCornerRadius,
        ),
        sheetContent = {
            coroutineScope.launch {
                sheetState.animateTo(ModalBottomSheetValue.Expanded)
            }
        }
    ) {}
}

// 무조건 버튼 눌러야 꺼지게 -> 전체화면 -> 밑에서 위로 올라오는 애니메이션 추가(필수)
// 전체화면이 아니라면? -> 굳이 해당 버튼을 다시 누를 일이 없는 버튼임. 일단 3초 뒤에 내려가도록 설정


@Composable
fun LogoutPopupScreen() { //로그아웃 팝업창
    var logoutPopupScreen by remember { mutableStateOf(true) }
    var logoutConfirmPopup by remember { mutableStateOf(false) }

    if (logoutPopupScreen) {
        AlertDialog(
            onDismissRequest = { logoutPopupScreen = false },
            shape = RoundedCornerShape(12.dp),
            title = {
                Text(
                    text = "로그아웃하시겠습니까?",
                    fontFamily = suite,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 17.sp,
                    color = colors.onPrimary
                )
            },
            text = {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "해당 계정으로 다시 로그인할 수 있습니다.",
                    fontFamily = suite,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            },
            buttons = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextButton( //취소 버튼
                        onClick = {
                            logoutPopupScreen = false
                        },
                        modifier = Modifier
                            .width(100.dp)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "취소",
                            fontFamily = suite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = colors.onPrimary
                        )
                    }

                    TextButton( //로그아웃 버튼
                        onClick = {
                            logoutPopupScreen = false
                            logoutConfirmPopup = true
                        },
                        modifier = Modifier
                            .width(100.dp)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "로그아웃",
                            fontFamily = suite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = Red
                        )
                    }
                }
            }
        )
    }

    if (logoutConfirmPopup) {
        AlertDialog( //로그아웃 후 팝업창
            onDismissRequest = { logoutConfirmPopup = false },
            shape = RoundedCornerShape(12.dp),
            text = {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "로그아웃이 완료되었습니다.",
                    fontFamily = suite,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            },

            buttons = {
                Row {
                    TextButton( //확인 버튼
                        onClick = {
                            logoutConfirmPopup = false
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "확인",
                            fontFamily = suite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = colors.onPrimary
                        )
                    }
                }
            }
        )
    }
} //로그아웃 페이지

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun AppInfoScreen(
    navController: NavHostController,
    names: List<String> = listOf("오픈 소스 라이브러리", "정보 2", "정보 3")
) {
// onClick안에 navControllerClick(navController, BottomScreen.Setting)이거 넣음 됨.
    var appInfoScreen by remember { mutableStateOf(true) }
    var infoBottomSheet by remember { mutableStateOf("null") }

    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )
    val roundedCornerRadius = 12.dp

    if (appInfoScreen) {
        ModalBottomSheetLayout(
            sheetState = modalSheetState,
            sheetShape = RoundedCornerShape(
                topStart = roundedCornerRadius,
                topEnd = roundedCornerRadius
            ),
            sheetContent = {
                coroutineScope.launch {
                    modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
                }

                Column(modifier = Modifier.fillMaxHeight()) {
                    MaterialTheme { //맨 위의 앱 바
                        TopAppBar {
                            Box(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Button( //뒤로 가기 버튼
                                    onClick = {
                                        coroutineScope.launch { modalSheetState.hide() }
                                    }
                                ) {
                                    Text(
                                        text = "<",
                                        fontFamily = suite,
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 25.sp,
                                        color = Color.Black
                                    )
                                }

                                Text(
                                    text = "정보",
                                    fontFamily = suite,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 20.sp,
                                    color = Color.Black,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    }
                    for (name in names) {
                        Surface(
                            modifier = Modifier.background(colors.primary)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Button(
                                    onClick = {
                                        when (name) {
                                            "오픈 소스 라이브러리" -> {
                                                infoBottomSheet = "오픈 소스 라이브러리"
                                            }
                                            "정보 2" -> {
                                                infoBottomSheet = "정보 2"
                                            }
                                            "정보 3" -> {
                                                infoBottomSheet = "정보 3"
                                            }
                                        }
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = name,
                                        fontFamily = suite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 15.sp,
                                        color = Color.Black,
                                        modifier = Modifier
                                            .padding(vertical = 20.dp)
                                            .padding(horizontal = 10.dp)
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }

                }
            }
        ) {}
    }
    when (infoBottomSheet) {
        "오픈 소스 라이브러리" -> {
            Info_OpenSourceLibrary(navController)
        }
        "정보 2" -> {

        }
        "정보 3" -> {

        }
    }
} //앱 정보 페이지

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Info_OpenSourceLibrary(
    navController: NavHostController
) {
    var appInfoScreen by remember { mutableStateOf(true) }

    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )
    val roundedCornerRadius = 12.dp

    if (appInfoScreen) {
        ModalBottomSheetLayout(
            sheetState = modalSheetState,
            sheetShape = RoundedCornerShape(
                topStart = roundedCornerRadius,
                topEnd = roundedCornerRadius
            ),
            sheetContent = {
                coroutineScope.launch {
                    modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
                }

                Column {
                    MaterialTheme { //맨 위의 앱 바
                        TopAppBar {
                            Box(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Button( //뒤로 가기 버튼
                                    onClick = {
                                        coroutineScope.launch { modalSheetState.hide() }
                                    }
                                ) {
                                    Text(
                                        text = "<",
                                        fontFamily = suite,
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 25.sp,
                                        color = Color.Black
                                    )
                                }

                                Text(
                                    text = "오픈 소스 라이브러리",
                                    fontFamily = suite,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 20.sp,
                                    color = Color.Black,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    }
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas ut mauris quis nisi blandit varius eu in neque. Nam vel ex et dolor vehicula bibendum sed eu urna. Sed condimentum augue nec orci blandit, eu egestas quam gravida. Nullam bibendum orci id ligula bibendum dictum. Suspendisse ac tellus neque. Pellentesque feugiat magna vitae hendrerit feugiat. Fusce vehicula elit ut elit egestas, sit amet dapibus sapien maximus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aenean id orci ut purus rutrum pretium eu ut lacus. Praesent bibendum quam quis blandit luctus. Donec id risus sit amet dolor lobortis laoreet.",
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        ) {}
    }
}
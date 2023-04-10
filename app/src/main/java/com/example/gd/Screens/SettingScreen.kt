package com.example.gd.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
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
    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        MaterialTheme { //맨 위의 앱 바
            TopAppBar {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "설정",
                        fontFamily = suite,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                }
            }
        }

        for (name in names) { //설정 목록 버튼 4개 생성
            Surface(
                modifier = Modifier.background(MaterialTheme.colors.primary)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = {
                            when (name) {
                                "프로필 수정" -> {
                                    settingScreen = "프로필 수정"
                                }
                                "회원 탈퇴" -> {
                                    settingScreen = "회원 탈퇴"
                                }
                                "로그아웃" -> {
                                    settingScreen = "로그아웃"
                                }
                                "정보" -> {
                                    settingScreen = "정보"
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
    println(settingScreen)
    when (settingScreen) {
        "프로필 수정" -> {
            ProfileEditScreen(
                sheetState = rememberModalBottomSheetState(
                    initialValue = ModalBottomSheetValue.Hidden,
                    confirmStateChange = { false }, // 드래그 방지
                    skipHalfExpanded = true
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileEditScreen(sheetState: ModalBottomSheetState) {
    val coroutineScope = rememberCoroutineScope()

    val roundedCornerRadius = 12.dp
    val modifier = Modifier.fillMaxSize()

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
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            sheetState.hide()
                            settingScreen = "default"
                        }

                    }
                ) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
    ) {
    }
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
                    color = Color.Black
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
                            color = Color.Black
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
                            color = Color.Black
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
                            modifier = Modifier.background(MaterialTheme.colors.primary)
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
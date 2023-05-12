package com.example.gd.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gd.Effects.ConfirmDismissPopupFormat
import com.example.gd.Effects.TopAppBarScreenFormat
import com.example.gd.ui.theme.suite
import kotlinx.coroutines.launch

var settingScreen by mutableStateOf("default")
//뒤로가기 시 초기화를 위한 전역변수

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable

fun SettingScreen(navController: NavController) {
    TopAppBarScreenFormat(
        titleText = "설정",
        IsLeftButton = false,
        IsRightButton = false,
        content = { SettingScreenContent() },
        leftButtonClick = {}, rightButtonClick = {}
    )
    when (settingScreen) {
        "오픈 소스 라이브러리" -> {
            OpenSourceLibraryScreen(
                sheetState = rememberModalBottomSheetState(
                    initialValue = ModalBottomSheetValue.Hidden,
                    confirmStateChange = { false } // 드래그 방지
                )
            )
        }
        "이용약관" -> {
            TermsConditionsScreen(
                sheetState = rememberModalBottomSheetState(
                    initialValue = ModalBottomSheetValue.Hidden,
                    confirmStateChange = { false } // 드래그 방지
                )
            )
        }
        "개인정보 처리방침" -> {
            PrivacyPolicyScreen(
                sheetState = rememberModalBottomSheetState(
                    initialValue = ModalBottomSheetValue.Hidden,
                    confirmStateChange = { false } // 드래그 방지
                )
            )
        }
        "회원 탈퇴" -> {
            WithdrawalScreen(
                sheetState = rememberModalBottomSheetState(
                    initialValue = ModalBottomSheetValue.Hidden,
                    confirmStateChange = { false } // 드래그 방지
                )
            )
        }
        "로그아웃" -> {
            LogoutPopupScreen()
        }
    }
}

@Composable
fun SettingScreenContent(
    names: List<String> =
        listOf("오픈 소스 라이브러리", "이용약관", "개인정보 처리방침", "회원 탈퇴", "로그아웃"),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp)
    ) {
        for (i in names.indices) { //설정 목록 버튼 생성
            Surface(
                modifier = Modifier.background(colors.primary)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = {
                            when (names[i]) {
                                "오픈 소스 라이브러리" -> {
                                    settingScreen = "오픈 소스 라이브러리"
                                }
                                "이용약관" -> {
                                    settingScreen = "이용약관"
                                }
                                "개인정보 처리방침" -> {
                                    settingScreen = "개인정보 처리방침"
                                }
                                "회원 탈퇴" -> {
                                    settingScreen = "회원 탈퇴"
                                }
                                "로그아웃" -> {
                                    settingScreen = "로그아웃"
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = names[i],
                            fontFamily = suite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                            color = if (names[i] == "로그아웃") Red else colors.onPrimary,
                            modifier = Modifier
                                .padding(vertical = 20.dp)
                                .padding(horizontal = 8.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                    Divider(
                        color = colors.secondaryVariant,
                        thickness = 1.dp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OpenSourceLibraryScreen(sheetState: ModalBottomSheetState) {
    var openSourceLibraryScreen by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    if (openSourceLibraryScreen) {
        ModalBottomSheetLayout(
            sheetState = sheetState,
            sheetContent = {
                coroutineScope.launch {
                    sheetState.animateTo(ModalBottomSheetValue.Expanded)
                }
                TopAppBarScreenFormat(
                    titleText = "오픈 소스 라이브러리",
                    IsLeftButton = true,
                    IsRightButton = false,
                    content = {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Column(
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .padding(24.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Text(
                                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas ut mauris quis nisi blandit varius eu in neque. Nam vel ex et dolor vehicula bibendum sed eu urna. Sed condimentum augue nec orci blandit, eu egestas quam gravida. Nullam bibendum orci id ligula bibendum dictum. Suspendisse ac tellus neque. Pellentesque feugiat magna vitae hendrerit feugiat. Fusce vehicula elit ut elit egestas, sit amet dapibus sapien maximus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aenean id orci ut purus rutrum pretium eu ut lacus. Praesent bibendum quam quis blandit luctus. Donec id risus sit amet dolor lobortis laoreet.",
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    },
                    leftButtonClick = {
                        coroutineScope.launch {
                            sheetState.hide()
                            settingScreen = "default"
                        }
                    },
                    rightButtonClick = { }
                )
            }
        ) {}
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TermsConditionsScreen(sheetState: ModalBottomSheetState) {
    var termsConditionsScreen by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    if (termsConditionsScreen) {
        ModalBottomSheetLayout(
            sheetState = sheetState,
            sheetContent = {
                coroutineScope.launch {
                    sheetState.animateTo(ModalBottomSheetValue.Expanded)
                }
                TopAppBarScreenFormat(
                    titleText = "이용약관",
                    IsLeftButton = true,
                    IsRightButton = false,
                    content = {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Column(
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .padding(24.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Text(
                                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas ut mauris quis nisi blandit varius eu in neque. Nam vel ex et dolor vehicula bibendum sed eu urna. Sed condimentum augue nec orci blandit, eu egestas quam gravida. Nullam bibendum orci id ligula bibendum dictum. Suspendisse ac tellus neque. Pellentesque feugiat magna vitae hendrerit feugiat. Fusce vehicula elit ut elit egestas, sit amet dapibus sapien maximus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aenean id orci ut purus rutrum pretium eu ut lacus. Praesent bibendum quam quis blandit luctus. Donec id risus sit amet dolor lobortis laoreet.",
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    },
                    leftButtonClick = {
                        coroutineScope.launch {
                            sheetState.hide()
                            settingScreen = "default"
                        }
                    },
                    rightButtonClick = { }
                )
            }
        ) {}
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PrivacyPolicyScreen(sheetState: ModalBottomSheetState) {
    var privacyPolicyScreen by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    if (privacyPolicyScreen) {
        ModalBottomSheetLayout(
            sheetState = sheetState,
            sheetContent = {
                coroutineScope.launch {
                    sheetState.animateTo(ModalBottomSheetValue.Expanded)
                }
                TopAppBarScreenFormat(
                    titleText = "개인정보 처리방침",
                    IsLeftButton = true,
                    IsRightButton = false,
                    content = {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Column(
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .padding(24.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Text(
                                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas ut mauris quis nisi blandit varius eu in neque. Nam vel ex et dolor vehicula bibendum sed eu urna. Sed condimentum augue nec orci blandit, eu egestas quam gravida. Nullam bibendum orci id ligula bibendum dictum. Suspendisse ac tellus neque. Pellentesque feugiat magna vitae hendrerit feugiat. Fusce vehicula elit ut elit egestas, sit amet dapibus sapien maximus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aenean id orci ut purus rutrum pretium eu ut lacus. Praesent bibendum quam quis blandit luctus. Donec id risus sit amet dolor lobortis laoreet.",
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    },
                    leftButtonClick = {
                        coroutineScope.launch {
                            sheetState.hide()
                            settingScreen = "default"
                        }
                    },
                    rightButtonClick = { }
                )
            }
        ) {}
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WithdrawalScreen(sheetState: ModalBottomSheetState) {
    val coroutineScope = rememberCoroutineScope()
    var popup by remember { mutableStateOf(false) }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            coroutineScope.launch {
                sheetState.animateTo(ModalBottomSheetValue.Expanded)
            }
            TopAppBarScreenFormat(
                titleText = "회원 탈퇴",
                IsLeftButton = true,
                IsRightButton = false,
                leftButtonClick = {
                    coroutineScope.launch {
                        sheetState.hide()
                        settingScreen = "default"
                    }
                },
                rightButtonClick = { },
                content = {
                    Column(modifier = Modifier.fillMaxHeight()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.8f)
                        ) {
                            Column(
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .padding(24.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Text(
                                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas ut mauris quis nisi blandit varius eu in neque. Nam vel ex et dolor vehicula bibendum sed eu urna. Sed condimentum augue nec orci blandit, eu egestas quam gravida. Nullam bibendum orci id ligula bibendum dictum. Suspendisse ac tellus neque. Pellentesque feugiat magna vitae hendrerit feugiat. Fusce vehicula elit ut elit egestas, sit amet dapibus sapien maximus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aenean id orci ut purus rutrum pretium eu ut lacus. Praesent bibendum quam quis blandit luctus. Donec id risus sit amet dolor lobortis laoreet.",
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.2f)
                                .padding(24.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Button(
                                onClick = {
                                    coroutineScope.launch {
                                        popup = true
                                    }
                                },
                                elevation = ButtonDefaults.elevation(
                                    defaultElevation = 0.dp,
                                    pressedElevation = 0.dp,
                                    disabledElevation = 0.dp
                                )
                            ) {
                                Text(
                                    text = "회원탈퇴",
                                    fontFamily = suite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 15.sp,
                                    color = Red
                                )
                            }
                        }
                    }
                }
            )
            if (popup) {
                var withdrawalConfirmPopup by remember { mutableStateOf(true) }
                var withdrawalCompletePopup by remember { mutableStateOf(false) }

                if (withdrawalConfirmPopup) {
                    ConfirmDismissPopupFormat(
                        titleText = "회원탈퇴",
                        dialogText = "사용자의 모든 정보가 삭제됩니다.\n정말 탈퇴하시겠습니까?",
                        buttonText = "회원탈퇴",
                        buttonColor = Red,
                        runButtonClick = {
                            withdrawalConfirmPopup = false
                            withdrawalCompletePopup = true
                            // *** 탈퇴 후 메인 화면으로 이동 코드 작성 ***
                        },
                        dismissButtonClick = {
                            withdrawalConfirmPopup = false
                            settingScreen = "default"
                        },
                        ifDoubleButton = true
                    )
                }
                if (withdrawalCompletePopup) {
                    ConfirmDismissPopupFormat(
                        titleText = "회원탈퇴",
                        dialogText = "회원탈퇴가 완료되었습니다.",
                        buttonText = "확인",
                        buttonColor = colors.onPrimary,
                        runButtonClick = {
                            withdrawalCompletePopup = false
                            settingScreen = "default"
                        },
                        dismissButtonClick = {},
                        ifDoubleButton = false
                    )
                }
            }
        }
    ) {}
}

@Composable
fun LogoutPopupScreen() { //로그아웃 팝업창
    var logoutConfirmPopup by remember { mutableStateOf(true) }
    var logoutCompletePopup by remember { mutableStateOf(false) }

    if (logoutConfirmPopup) {
        ConfirmDismissPopupFormat(
            titleText = "로그아웃",
            dialogText = "해당 계정으로 다시 로그인할 수 있습니다.\n로그아웃하시겠습니까?",
            buttonText = "로그아웃",
            buttonColor = Red,
            runButtonClick = {
                logoutConfirmPopup = false
                logoutCompletePopup = true
                // *** 로그아웃 후 메인 화면으로 이동 코드 작성 ***
            },
            dismissButtonClick = {
                logoutConfirmPopup = false
                settingScreen = "default"
            },
            ifDoubleButton = true
        )
    }
    if (logoutCompletePopup) {
        ConfirmDismissPopupFormat(
            titleText = "로그아웃 완료",
            dialogText = "로그아웃이 완료되었습니다.",
            buttonText = "확인",
            buttonColor = colors.onPrimary,
            runButtonClick = {
                logoutCompletePopup = false
                settingScreen = "default"
            },
            dismissButtonClick = {},
            ifDoubleButton = false
        )
    }
}
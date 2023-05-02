package com.example.gd.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gd.R
import com.example.gd.ui.theme.suite
import kotlinx.coroutines.launch

var settingScreen by mutableStateOf("default")
var settingInfoScreen by mutableStateOf("default")

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingScreen(navController: NavHostController) {
        TopAppBarScreenFormat(
            titleText = "설정",
            IsLeftButton = false,
            IsRightButton = false,
            content = { SettingScreenContent() },
            leftButtonClick = {}, rightButtonClick = {}
        )
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
        "정보" -> {
            AppInfoScreen(
                sheetState = rememberModalBottomSheetState(
                    initialValue = ModalBottomSheetValue.Hidden,
                    confirmStateChange = { false } // 드래그 방지 //
                )
            )
        }
    }
}

@Composable
fun SettingScreenContent(
    names: List<String> =
        listOf("프로필 수정", "회원 탈퇴", "로그아웃", "정보"),
    imageVector: List<ImageVector> =
        listOf(
            Icons.Filled.ArrowBack,
            Icons.Filled.ArrowBack,
            Icons.Filled.ArrowBack,
            Icons.Filled.ArrowBack
        ),
    contentDescription: List<String> =
        listOf("프로필 수정", "회원 탈퇴", "로그아웃", "정보")
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp)
    ) {
        for (i in names.indices) { //설정 목록 버튼 4개 생성
            Surface(
                modifier = Modifier.background(colors.primary)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = {
                            when (names[i]) {
                                "프로필 수정" -> { settingScreen = "프로필 수정" }
                                "회원 탈퇴" -> { settingScreen = "회원 탈퇴" }
                                "로그아웃" -> { settingScreen = "로그아웃" }
                                "정보" -> { settingScreen = "정보" }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = imageVector[i],
                            contentDescription = contentDescription[i],
                            tint = colors.onPrimary
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = names[i],
                            fontFamily = suite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                            color = colors.onPrimary,
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TopAppBarScreenFormat(
    titleText: String,
    IsLeftButton: Boolean,
    IsRightButton: Boolean,
    content: @Composable () -> Unit,
    leftButtonClick: () -> Unit,
    rightButtonClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                contentColor = colors.onPrimary,
                backgroundColor = colors.primary,
                title = {
                    Text(
                        text = titleText,
                        fontFamily = suite,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    if (IsLeftButton) {
                        IconButton(onClick = { leftButtonClick() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back Button",
                                tint = colors.onPrimary
                            )
                        }
                    }
                },
                elevation = 8.dp,
                actions = {
                    if (IsRightButton) {
                        IconButton(onClick = { rightButtonClick() }) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "Save Button",
                                tint = colors.primaryVariant
                            )
                        }
                    }
                }
            )
        }
    ) {
        content()
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextFieldFormat(fieldTitle: String) {
    var userValue by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = fieldTitle,
            fontFamily = suite,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            color = colors.secondary
        )
        TextField(
            value = userValue,
            onValueChange = { newText -> userValue = newText },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(
                onDone = {keyboardController?.hide()}
            ),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    if (!it.isFocused) { keyboardController?.hide() }
                },
            textStyle = TextStyle(
                fontFamily = suite,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = colors.onPrimary,
                textAlign = TextAlign.Start
            ),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = colors.primary,
                focusedIndicatorColor = Transparent,
                unfocusedIndicatorColor = Transparent
            )
        )
        Divider(
            color = colors.secondary,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ConfirmDismissPopupFormat(
    titleText: String,
    dialogText: String,
    buttonText: String,
    buttonColor: Color,
    runButtonClick: () -> Unit,
    dismissButtonClick: () -> Unit,
    ifDoubleButton: Boolean
) {
    AlertDialog(
        onDismissRequest = { },
        shape = RoundedCornerShape(12.dp),
        title = {
            Text(
                text = titleText,
                fontFamily = suite,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 17.sp,
                color = colors.onPrimary
            )
        },
        text = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = dialogText,
                fontFamily = suite,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = colors.secondary
            )
        },
        confirmButton = {
            TextButton(
                onClick = { runButtonClick() },
                modifier = Modifier
                    .width(100.dp)
                    .padding(8.dp)
            ) {
                Text(
                    text = buttonText,
                    fontFamily = suite,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    color = buttonColor
                )
            }
        },
        dismissButton = {
            if (ifDoubleButton) {
                TextButton(
                    onClick = { dismissButtonClick() },
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
            }
        }
    )
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileEditScreen(sheetState: ModalBottomSheetState) {
    val coroutineScope = rememberCoroutineScope()
    var popup by remember { mutableStateOf(false) }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            coroutineScope.launch {
                sheetState.animateTo(ModalBottomSheetValue.Expanded)
            }
            TopAppBarScreenFormat(
                titleText = "프로필 수정",
                IsLeftButton = true,
                IsRightButton = true,
                content = { ProfileEditContent() },
                leftButtonClick = {
                    coroutineScope.launch {
                        sheetState.hide()
                        settingScreen = "default"
                    }
                },
                rightButtonClick = { popup = true }
            )
            if (popup) {
                var profileEditConfirmPopup by remember { mutableStateOf(true) }
                var profileEditCompletePopup by remember { mutableStateOf(false) }

                if (profileEditConfirmPopup) {
                    ConfirmDismissPopupFormat(
                        titleText = "변경 사항 저장",
                        dialogText = "프로필 변경 사항을 저장하시겠습니까?",
                        buttonText = "저장",
                        buttonColor = colors.primaryVariant,
                        runButtonClick = {
                            profileEditConfirmPopup = false
                            profileEditCompletePopup = true
                            // *** 프로필 저장 기능 코드 작성 ***
                        },
                        dismissButtonClick = {
                            profileEditConfirmPopup = false
                            settingScreen = "default"
                        },
                        ifDoubleButton = true
                    )
                }
                if (profileEditCompletePopup) {
                    ConfirmDismissPopupFormat(
                        titleText = "변경 사항 저장",
                        dialogText = "프로필 변경 사항이 저장되었습니다.",
                        buttonText = "확인",
                        buttonColor = colors.onPrimary,
                        runButtonClick = {
                            profileEditCompletePopup = false
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
fun ProfileEditContent() {
    Column( //페이지 내용
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .padding(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(1.dp, colors.secondaryVariant, CircleShape)
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
                color = colors.primaryVariant
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

@OptIn(ExperimentalMaterialApi::class)
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
} //로그아웃 페이지

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun AppInfoScreen(
    sheetState: ModalBottomSheetState,
    names: List<String> = listOf("오픈 소스 라이브러리", "정보 2", "정보 3")
) {
// onClick안에 navControllerClick(navController, BottomScreen.Setting)이거 넣음 됨.
    var appInfoScreen by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    if (appInfoScreen) {
        ModalBottomSheetLayout(
            sheetState = sheetState,
            sheetContent = {
                coroutineScope.launch {
                    sheetState.animateTo(ModalBottomSheetValue.Expanded)
                }
                TopAppBarScreenFormat(
                    titleText = "정보",
                    IsLeftButton = true,
                    IsRightButton = false,
                    leftButtonClick = {
                        coroutineScope.launch {
                            coroutineScope.launch {
                                sheetState.hide()
                                settingScreen = "default"
                            }
                        }
                    },
                    rightButtonClick = { },
                    content = {
                        Column(modifier = Modifier.fillMaxSize()) {
                            for (name in names) {
                                Button(
                                    onClick = {
                                        when (name) {
                                            "오픈 소스 라이브러리" -> {
                                                settingInfoScreen = "오픈 소스 라이브러리"
                                            }
                                            "정보 2" -> {
                                                settingInfoScreen = "정보 2"
                                            }
                                            "정보 3" -> {
                                                settingInfoScreen = "정보 3"
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
                                        color = colors.onPrimary,
                                        modifier = Modifier
                                            .padding(vertical = 20.dp)
                                            .padding(horizontal = 10.dp)
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                )
            }
        ) {}
    }
    when (settingInfoScreen) {
        "오픈 소스 라이브러리" -> {
            Info_OpenSourceLibrary(
                sheetState = rememberModalBottomSheetState(
                    initialValue = ModalBottomSheetValue.Hidden,
                    confirmStateChange = { false } // 드래그 방지 //
                )
            )
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
private fun Info_OpenSourceLibrary(sheetState: ModalBottomSheetState) {
    var appInfoScreen by remember { mutableStateOf(true) }

    val coroutineScope = rememberCoroutineScope()

    if (appInfoScreen) {
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
                            settingInfoScreen = "default"
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
                        settingInfoScreen = "default"
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
                                        //sheetState.hide()
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
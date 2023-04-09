package com.example.gd.Screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gd.ui.theme.suite

@Composable
fun SettingScreen(navController: NavHostController) {
    SettingList()
}

@Composable
private fun SettingList(
    modifier: Modifier = Modifier,
    names: List<String> = listOf("프로필 수정", "회원 탈퇴", "로그아웃", "정보"),
) {
    var settingView by remember { mutableStateOf(true) }
    var profileView by remember { mutableStateOf(false) }
    var deleteView by remember { mutableStateOf(false) }
    var logoutView by remember { mutableStateOf(false) }
    var infoView by remember { mutableStateOf(false) }

    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color.White) //투명 상태여서 임시로 하얀 배경 깔아놓음
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
            Divider()
        }

        for (name in names) { //설정 목록 버튼 4개 생성
            Surface(
                modifier = Modifier
                    .padding(vertical = 1.dp, horizontal = 5.dp)
                    .background(MaterialTheme.colors.primary)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Button(
                        onClick = {
                            when (name) {
                                "프로필 수정" -> {
                                    settingView = false
                                    profileView = true
                                }
                                "회원 탈퇴" -> {
                                    settingView = false
                                    deleteView = true
                                    showDialog = true
                                }
                                "로그아웃" -> {
                                    settingView = false
                                    logoutView = true
                                }
                                "정보" -> {
                                    settingView = false
                                    infoView = true
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
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
    if (profileView) {
        Profile()
    } else if (deleteView) {
        ShowDialog(
            "회원 탈퇴", "정말 탈퇴하시겠습니까?", "회원 탈퇴가 완료되었습니다."
        )
    } else if (logoutView) {
        LogoutAccount()
    } else if (infoView) {
        Info()
    }
}



// *** 상세 페이지 ***
@Composable
private fun Profile() {
    var settingView by remember { mutableStateOf(false) }
    var profileView by remember { mutableStateOf(true) }

    Column {
        val context = LocalContext.current

        MaterialTheme { //맨 위의 앱 바
            TopAppBar {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                ) {
                    Button( //뒤로 가기 버튼
                        onClick = {
                            settingView = true
                            profileView = false
                        },
                        modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        BackScreen()
                    }

                    Text(
                        text = "프로필 수정",
                        fontFamily = suite,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.Center)
                    )

                    Button( //저장 버튼
                        onClick = {
                            settingView = true
                            profileView = false
                            Toast.makeText(
                                context, "저장되었습니다.", Toast.LENGTH_SHORT
                            ).show()
                        },
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        Text(
                            text = "V",
                            fontFamily = suite,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 25.sp,
                            color = Color.Blue
                        )
                    }
                }
            }
            Divider()
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 1.dp, horizontal = 5.dp)
                .background(MaterialTheme.colors.primary),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "프로필 수정 페이지")
        }
    }
    if (settingView) {
        SettingList()
    }
}

@Composable
private fun LogoutAccount() {
    var settingView by remember { mutableStateOf(false) }
    var logoutView by remember { mutableStateOf(true) }

    Column {
        MaterialTheme {
            TopAppBar {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                ) {
                    Button( //뒤로 가기 버튼
                        onClick = {
                            settingView = true
                            logoutView = false
                        },
                        modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        BackScreen()
                    }

                    Text(
                        text = "로그아웃",
                        fontFamily = suite,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            Divider()
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 1.dp, horizontal = 5.dp)
                .background(MaterialTheme.colors.primary),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "로그아웃 페이지")
        }
    }
    if (settingView) {
        SettingList()
    }
}

@Composable
private fun Info() {
    var settingView by remember { mutableStateOf(false) }
    var infoView by remember { mutableStateOf(true) }

    Column {
        MaterialTheme {
            TopAppBar {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                ) {
                    Button( //뒤로 가기 버튼
                        onClick = {
                            settingView = true
                            infoView = false
                        },
                        modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        BackScreen()
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
            Divider()
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 1.dp, horizontal = 5.dp)
                .background(MaterialTheme.colors.primary),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "정보 페이지")
        }
    }
    if (settingView) {
        SettingList()
    }
}


@Composable
fun Divider() { //상단 구분선
    Divider(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(bottom = 30.dp),
        color = Color.Black,
        thickness = 1.dp
    )
}

@Composable
fun BackScreen() { //뒤로 가기 버튼
    Text( //그림으로 대체 예정
        text = "<",
        fontFamily = suite,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 25.sp,
        color = Color.Black
    )
}

@Composable
fun ShowDialog(title: String, text: String, toast: String) { //회원 탈퇴, 로그아웃 팝업창
    var settingView by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(true) }
    val context = LocalContext.current

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "$title") },
            text = { Text(text = "$text") },
            confirmButton = {
                Row {
                    Button(
                        onClick = {
                            Toast.makeText(
                                context, "$toast", Toast.LENGTH_SHORT
                            ).show()
                            showDialog = false
                            settingView = true
                        },
                        modifier = Modifier
                            .width(100.dp)
                            .padding(8.dp)
                    ) {
                        Text(text = "예")
                    }
                    Button(
                        onClick = {
                            showDialog = false
                            settingView = true
                        },
                        modifier = Modifier
                            .width(100.dp)
                            .padding(8.dp)
                    ) {
                        Text(text = "아니요")
                    }
                }
            }
        )
    }
    if (settingView) {
        SettingList()
    }
}

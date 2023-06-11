package com.dongminpark.projectgd.Screens

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dongminpark.projectgd.Effects.ConfirmDismissPopupFormat
import com.dongminpark.projectgd.Effects.TopAppBarScreenFormat
import com.dongminpark.projectgd.Effects.profileEditScreenState
import com.dongminpark.projectgd.Retrofit.RetrofitManager
import com.dongminpark.projectgd.ui.theme.suite
import com.dongminpark.projectgd.Utils.RESPONSE_STATE
import com.dongminpark.projectgd.Utils.USER
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
        "개인정보 처리방침 및 이용약관" -> {
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
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LogoutPopupScreen()
            }
        }
    }
}

@Composable
fun SettingScreenContent(
    names: List<String> =
        listOf("오픈 소스 라이브러리", "개인정보 처리방침 및 이용약관", "회원 탈퇴", "로그아웃"),
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
                                "개인정보 처리방침 및 이용약관" -> {
                                    settingScreen = "개인정보 처리방침 및 이용약관"
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
                                    text = "* Retrofit2 : https://github.com/square/retrofit\n" +
                                            "* Accompanist-pager : https://google.github.io/accompanist/pager \n" +
                                            "* Android Navigation Common : https://developer.android.com/topic/libraries/architecture/index.html\n" +
                                            "* Android Navigation Fragment : https://developer.android.com/topic/libraries/architecture/index.html\n" +
                                            "* Android Lifecycle ReactiveStreams KTX : https://developer.android.com/topic/libraries/architecture/index.html\n" +
                                            "* Activity : https://developer.android.com/jetpack/androidx\n" +
                                            "* Material Components for Android : https://github.com/material-components/material-components-android\n" +
                                            "* Android AppCompat Library v7 : https://developer.android.com/jetpack/androidx\n" +
                                            "* Android ConstraintLayout : http://tools.android.com\n" +
                                            "* Android ConstraintLayout Solver : http://tools.android.com\n" +
                                            "* Coil : https://github.com/coil-kt/coil\n" +
                                            "* Glid : https://github.com/bumptech/glide\n" +
                                            "* Gson : https://github.com/google/gson\n" +
                                            "* okhttp : https://github.com/square/okhttp\n",
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
                    titleText = "개인정보 처리방침 및 이용약관",
                    IsLeftButton = true,
                    IsRightButton = false,
                    content = {
                        Box(modifier = Modifier.fillMaxSize()) {
                            LazyColumn(
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .padding(24.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                item {
                                    Text(
                                        text = "○ 이 개인정보처리방침은 2023년 6월 14일부터 적용됩니다.\n" +
                                                "\n" +
                                                "이집내집는 「개인정보 보호법」 제30조에 따라 정보주체의 개인정보를 보호하고 이와 관련한 고충을 신속하고 원활하게 처리할 수 있도록 하기 위하여 다음과 같이 개인정보 처리방침을 수립·공개합니다.\n" +
                                                "\n" +
                                                "제1조 (수집되는 개인정보와 처리 목적)\n" +
                                                "수집항목: 이름, 이메일, 프로필사진\n" +
                                                "보유기간: 탈퇴시까지 보유합니다. 또한, 이용목적이 달성되면 지체 없이 파기합니다.\n" +
                                                "\n" +
                                                "이집내집는 다음의 목적을 위하여 개인정보를 처리합니다. 처리하고 있는 개인정보는 다음의 목적 이외의 용도로는 이용되지 않으며 이용 목적이 변경되는 경우에는 「개인정보 보호법」 제18조에 따라 별도의 동의를 받는 등 필요한 조치를 이행할 예정입니다.\n" +
                                                "홈페이지/앱 서비스(‘웹: http://ezipnaezip.life/’, ‘앱: 이집내집’) 회원가입 및 관리\n" +
                                                "재화 또는 서비스 제공\n" +
                                                "마케팅 및 광고에의 활용\n" +
                                                "\n" +
                                                "제2조 (개인정보의 처리 및 보유 기간)\n" +
                                                "이집내집는 원활한 서비스 제공을 위해 원칙적으로 회원의 개인정보를 탈퇴시까지 보유합니다. 아울러 이용목적이 달성되면 지체 없이 파기합니다. 단, 다음의 정보에 대해서는 아래의 이유로 명시한 기간 동안 보존합니다.\n" +
                                                "\n" +
                                                "보유근거\t관련법령\t보존 기간\n" +
                                                "서비스 제공\t신용정보의 수집/처리 및 이용 등에 관한 기록\t3년\n" +
                                                "서비스 제공\t소비자의 불만 또는 분쟁처리에 관한 기록\t3년\n" +
                                                "서비스 제공\t대금결제 및 재화 등의 공급에 관한 기록\t5년\n" +
                                                "서비스 제공\t계약 또는 청약철회 등에 관한 기록\t5년\n" +
                                                "서비스 제공\t표시/광고에 관한 기록\t6개월\n" +
                                                "\n" +
                                                "제3조 (정보주체와 법정대리인의 권리·의무 및 그 행사방법)\n" +
                                                "정보주체는 이집내집에 대해 언제든지 개인정보 열람·정정·삭제·처리정지 요구 등의 권리를 행사할 수 있습니다. 제1항에 따른 권리 행사는 이집내집에 대해 「개인정보 보호법」 시행령 제41조 제1항에 따라 서면, 전자우편, 모사전송(FAX) 등을 통하여 하실 수 있으며 이집내집는 이에 대해 지체 없이 조치할 예정입니다. 개인정보 열람 및 처리정지 요구는 「개인정보 보호법」 제35조 제4항, 제37조 제2항에 의하여 정보주체의 권리가 제한될 수 있습니다. 개인정보의 정정 및 삭제 요구는 다른 법령에서 그 개인정보가 수집 대상으로 명시되어 있는 경우에는 그 삭제를 요구할 수 없습니다. 이집내집는 정보주체 권리에 따른 열람의 요구, 정정·삭제의 요구, 처리정지의 요구 시 열람 등 요구를 한 자가 본인이거나 정당한 대리인인지를 확인합니다.\n" +
                                                "\n" +
                                                "제4조 (처리하는 개인정보의 항목)\n" +
                                                "이집내집는 다음의 개인정보 항목을 처리하고 있습니다.\n" +
                                                "회원 필수항목: 이름, 이메일, 프로필사진\n" +
                                                "서비스 이용과정이나 사업처리 과정에서 다음과 같은 정보들이 자동으로 생성되어 수집될 수 있습니다: IP Address, 쿠키, 방문 일시, 서비스 이용 기록, 불량 이용 기록\n" +
                                                "\n" +
                                                "제5조 (개인정보의 파기)\n" +
                                                "이집내집는 개인정보 보유기간의 경과, 처리목적 달성 등 개인정보가 불필요하게 되었을 때에는 지체없이 해당 개인정보를 파기합니다. 정보주체로부터 동의받은 개인정보 보유기간이 경과하거나 처리목적이 달성되었음에도 불구하고 다른 법령에 따라 개인정보를 계속 보존하여야 하는 경우에는, 해당 개인정보를 별도의 데이터베이스(DB)로 옮기거나 보관장소를 달리하여 보존합니다. 개인정보 파기의 절차 및 방법은 다음과 같습니다.\n" +
                                                "파기절차: 이집내집는 파기 사유가 발생한 개인정보를 선정하고, 개인정보 보호책임자의 승인을 받아 개인정보를 파기합니다.\n" +
                                                "파기방법: 전자적 파일 형태의 정보는 기록을 재생할 수 없는 기술적 방법을 사용합니다.\n" +
                                                "\n" +
                                                "제6조 (개인정보의 안전성 확보 조치)\n" +
                                                "이집내집는 개인정보의 안전성 확보를 위해 다음과 같은 조치를 취하고 있습니다.\n" +
                                                "개인정보 취급 인원 최소화: 개인정보를 취급하는 사람을 담당자에 한정시켜 최소화하여 개인정보를 관리하는 대책을 시행하고 있습니다.\n" +
                                                "내부관리계획의 수립 및 시행: 개인정보의 안전한 처리를 위하여 내부관리계획을 수립하고 시행하고 있습니다.\n" +
                                                "개인정보의 암호화: 회원 개인정보 중 비밀번호는 암호화되어 저장 및 관리되고 있어, 본인만이 알 수 있으며 중요한 데이터는 파일 및 전송 데이터를 암호화하거나 파일 잠금 기능을 사용하는 등의 별도 보안기능을 사용하고 있습니다.\n" +
                                                "접속기록의 보관 및 위변조 방지: 개인정보처리시스템에 접속한 기록을 보관, 관리하고 있습니다.\n" +
                                                "개인정보에 대한 접근 제한: 개인정보를 처리하는 데이터베이스시스템에 대한 접근권한의 부여, 변경, 말소를 통하여 개인정보에 대한 접근통제를 위하여 필요한 조치를 하고 있으며 외부로부터의 무단 접근을 통제하고 있습니다.\n" +
                                                "\n" +
                                                "제7조 (개인정보 자동 수집 장치의 설치·운영 및 거부에 관한 사항)\n" +
                                                "이집내집는 회원에게 개별적인 맞춤서비스를 제공하기 위해 이용정보를 저장하고 수시로 불러오는 ‘쿠키(cookie)’를 사용합니다.\n" +
                                                "쿠키는 웹사이트를 운영하는데 이용되는 서버(http)가 회원의 컴퓨터 브라우저에게 보내는 소량의 정보이며 회원들의 PC 컴퓨터 내의 하드디스크에 저장되기도 합니다.\n" +
                                                "쿠키의 사용 목적: 회원이 방문한 각 서비스와 웹 사이트들에 대한 방문 및 이용형태, 인기 검색어, 보안접속 여부 등을 파악하여 회원에게 최적화된 정보 제공을 위해 사용됩니다.\n" +
                                                "쿠키의 설치·운영 및 거부: 웹브라우저 상단의 도구>인터넷 옵션>개인정보 메뉴의 옵션 설정을 통해 쿠키 저장을 거부할 수 있습니다. 단, 쿠키 저장을 거부할 경우 맞춤형 서비스 이용에 어려움이 발생할 수 있습니다.\n" +
                                                "\n" +
                                                "제8조 (개인정보 보호책임자)\n" +
                                                "이집내집는 개인정보 처리에 관한 업무를 총괄해서 책임지고, 개인정보 처리와 관련한 정보주체의 불만처리 및 피해구제 등을 위하여 아래와 같이 개인정보 보호책임자를 지정하고 있습니다.\n" +
                                                "\n" +
                                                "개인정보 보호책임자\n" +
                                                "성명: 김관식\n" +
                                                "직급: 공동대표\n" +
                                                "직책: CTO\n" +
                                                "연락처: 010-4865-1362, seorit@kyonggi.ac.kr\n" +
                                                "정보주체께서는 이집내집의 서비스(또는 사업)을 이용하시면서 발생한 모든 개인정보 보호 관련 문의, 불만처리, 피해구제 등에 관한 사항을 개인정보 보호책임자 및 담당부서로 문의하실 수 있습니다. 이집내집는 정보주체의 문의에 대해 지체 없이 답변 및 처리해드릴 것입니다.\n" +
                                                "\n" +
                                                "제9조 (개인정보 열람청구)\n" +
                                                "정보주체는 ｢개인정보 보호법｣ 제35조에 따른 개인정보의 열람 청구를 아래의 부서에 할 수 있습니다.\n" +
                                                "이집내집는 정보주체의 개인정보 열람청구가 신속하게 처리되도록 노력하겠습니다.\n" +
                                                "\n" +
                                                "개인정보 열람청구 접수·처리 담당자\n" +
                                                "성명: 김관식\n" +
                                                "직급: 공동대표\n" +
                                                "직책: CTO\n" +
                                                "연락처: 010-4865-1362, seorit@kyonggi.ac.kr\n" +
                                                "\n" +
                                                "제10조 (권익침해 구제방법)\n" +
                                                "정보주체는 개인정보침해로 인한 구제를 받기 위하여 개인정보분쟁조정위원회, 한국인터넷진흥원 개인정보침해신고센터 등에 분쟁해결이나 상담 등을 신청할 수 있습니다. 이 밖에 기타 개인정보침해의 신고, 상담에 대하여는 아래의 기관에 문의하시기 바랍니다.\n" +
                                                "개인정보분쟁조정위원회: (국번없이) 1833-6972 (www.kopico.go.kr)\n" +
                                                "개인정보침해신고센터: (국번없이) 118 (privacy.kisa.or.kr)\n" +
                                                "대검찰청 사이버수사과: (국번없이) 1301 (www.spo.go.kr)\n" +
                                                "경찰청 사이버안전국: (국번없이) 182 (cyberbureau.police.go.kr)",
                                    )
                                }
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
    var viewModel : MyViewModel = viewModel()
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
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ConfirmDismissPopupFormat(
                            titleText = "회원탈퇴",
                            dialogText = "사용자의 모든 정보가 삭제됩니다.\n정말 탈퇴하시겠습니까?",
                            buttonText = "회원탈퇴",
                            buttonColor = Red,
                            runButtonClick = {
                                withdrawalConfirmPopup = false
                                withdrawalCompletePopup = true
                                revoke()
                            },
                            dismissButtonClick = {
                                withdrawalConfirmPopup = false
                                settingScreen = "default"
                            },
                            ifDoubleButton = true,
                            onDismiss = {
                                withdrawalConfirmPopup = false
                                settingScreen = "default"
                            }
                        )
                    }
                }
                if (withdrawalCompletePopup) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ConfirmDismissPopupFormat(
                            titleText = "회원탈퇴",
                            dialogText = "회원탈퇴가 완료되었습니다.",
                            buttonText = "확인",
                            buttonColor = colors.onPrimary,
                            runButtonClick = {
                                withdrawalCompletePopup = false
                                settingScreen = "default"
                                restartApp(viewModel)
                            },
                            dismissButtonClick = {},
                            ifDoubleButton = false,
                            onDismiss = {
                                withdrawalCompletePopup = false
                                settingScreen = "default"
                            }
                        )
                    }
                }
            }
        }
    ) {}
}

@Composable
fun LogoutPopupScreen() { //로그아웃 팝업창
    var viewModel : MyViewModel = viewModel()
    var logoutConfirmPopup by remember { mutableStateOf(true) }
    var logoutCompletePopup by remember { mutableStateOf(false) }

    if (logoutConfirmPopup) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ConfirmDismissPopupFormat(
                titleText = "로그아웃",
                dialogText = "해당 계정으로 다시 로그인할 수 있습니다.\n로그아웃하시겠습니까?",
                buttonText = "로그아웃",
                buttonColor = Red,
                runButtonClick = {
                    logoutConfirmPopup = false
                    logoutCompletePopup = true
                    onLogout()
                },
                dismissButtonClick = {
                    logoutConfirmPopup = false
                    settingScreen = "default"
                },
                ifDoubleButton = true,
                onDismiss = {
                    logoutConfirmPopup = false
                    settingScreen = "default"
                }
            )
        }
    }
    if (logoutCompletePopup) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ConfirmDismissPopupFormat(
                titleText = "로그아웃 완료",
                dialogText = "로그아웃이 완료되었습니다.",
                buttonText = "확인",
                buttonColor = colors.onPrimary,
                runButtonClick = {
                    logoutCompletePopup = false
                    settingScreen = "default"
                    restartApp(viewModel)
                },
                dismissButtonClick = {},
                ifDoubleButton = false,
                onDismiss = {
                    logoutCompletePopup = false
                    settingScreen = "default"
                }
            )
        }
    }
}

fun onLogout() {
    OAuthData.mGoogleSignInClient!!.signOut().addOnCompleteListener {  }
    OAuthData.auth = null
    Log.d("LogOut", "로그아웃하셨습니다.")
}

fun restartApp(viewModel: MyViewModel) {
    val context = viewModel.appContext
    viewModel.resetVariables()
    val packageManager = context.packageManager
    val launchIntent = packageManager.getLaunchIntentForPackage(context.packageName)
    launchIntent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    context.startActivity(launchIntent)
}

fun revoke(){
    OAuthData.mGoogleSignInClient!!.revokeAccess()
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                OAuthData.auth!!.getCurrentUser()!!.delete()
                RetrofitManager.instance.userUserUserId(USER.USERID, completion = {
                    responseState ->
                    when(responseState){
                        RESPONSE_STATE.OKAY ->{

                        }
                        RESPONSE_STATE.FAIL ->{

                        }
                    }
                })
            } else {
                // 구글 인증 해제 실패
                // 실패 처리를 진행할 수 있습니다.
            }
        }
}

class MyViewModel(application: Application) : AndroidViewModel(application) {
    val appContext: Context = getApplication()

    fun resetVariables() {
        profileEditScreenState = "default"
    }
}
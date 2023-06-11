package com.dongminpark.projectgd.Screens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dongminpark.projectgd.App
import com.dongminpark.projectgd.Effects.*
import com.dongminpark.projectgd.Model.FollowUser
import com.dongminpark.projectgd.Model.Post
import com.dongminpark.projectgd.Model.User
import com.dongminpark.projectgd.Retrofit.RetrofitManager
import com.dongminpark.projectgd.ui.IconPack
import com.dongminpark.projectgd.ui.theme.suite
import com.dongminpark.projectgd.Utils.Constants.TAG
import com.dongminpark.projectgd.Utils.MESSAGE
import com.dongminpark.projectgd.Utils.RESPONSE_STATE
import com.dongminpark.projectgd.Utils.USER
import com.ezipnaezip.gd.ui.iconpack.Right
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

val mybookmarkArr = arrayListOf<Post>()
val mymyArr = arrayListOf<Post>()
var myinfo = User()
var follower = arrayListOf<FollowUser>()
var following = arrayListOf<FollowUser>()

@SuppressLint("UnrememberedMutableState")
@Composable
fun MyScreen(navController: NavController) {
    var loading by rememberSaveable{ mutableStateOf(true) }
    var isOkay by rememberSaveable{ mutableStateOf(false) }

    if (loading) {
        loading = false
        mybookmarkArr.clear()
        mymyArr.clear()
        RetrofitManager.instance.userInfoUserid(
            USER.USERID,
            completion = { responseState, bookmarkArr, myArr, info ->

                when (responseState) {
                    RESPONSE_STATE.OKAY -> {
                        Log.d(TAG, "api 호출 성공")
                        mybookmarkArr.addAll(bookmarkArr!!)
                        mymyArr.addAll(myArr!!)
                        myinfo = info!!
                        isOkay = true
                    }
                    RESPONSE_STATE.FAIL -> {
                        Toast.makeText(App.instance, MESSAGE.ERROR, Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "api 호출 에러")
                    }
                }
            })
    }

    if (isOkay) UserMyContent(navController, "my", mybookmarkArr, mymyArr, myinfo)
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
fun ButtonFormat(text1: String, text2: String, userInfo: User, navController: NavController, route: String) {
    var followerBool by remember { mutableStateOf(false) }
    var followingBool by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .width(90.dp)
            .clickable {
                when (text2) {
                    "Following" -> {
                        RetrofitManager.instance.followFollowingsUserId(
                            userInfo.userId,
                            completion = { responseState, resposeBody ->
                                when (responseState) {
                                    RESPONSE_STATE.OKAY -> {
                                        following.clear()
                                        following = resposeBody!!
                                        followerBool = true
                                        if (followerBool && followingBool) navController.navigate("${route}_follow_screen/FOLLOWING")
                                    }
                                    RESPONSE_STATE.FAIL -> {
                                        Toast
                                            .makeText(
                                                App.instance,
                                                MESSAGE.ERROR,
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                        Log.d(TAG, "MainScreen: examples load Error")
                                    }
                                }
                            })
                        RetrofitManager.instance.followFollowersUserId(
                            userInfo.userId,
                            completion = { responseState, resposeBody ->
                                when (responseState) {
                                    RESPONSE_STATE.OKAY -> {
                                        follower.clear()
                                        follower = resposeBody!!
                                        followingBool = true
                                        if (followerBool && followingBool) navController.navigate("${route}_follow_screen/FOLLOWING")
                                    }
                                    RESPONSE_STATE.FAIL -> {
                                        Toast
                                            .makeText(
                                                App.instance,
                                                MESSAGE.ERROR,
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                        Log.d(TAG, "MainScreen: examples load Error")
                                    }
                                }
                            })
                    }
                    "Follower" -> {
                        RetrofitManager.instance.followFollowingsUserId(
                            userInfo.userId,
                            completion = { responseState, resposeBody ->
                                when (responseState) {
                                    RESPONSE_STATE.OKAY -> {
                                        following.clear()
                                        following = resposeBody!!
                                        followerBool = true
                                        if (followerBool && followingBool) navController.navigate("${route}_follow_screen/FOLLOWER")
                                    }
                                    RESPONSE_STATE.FAIL -> {
                                        Toast
                                            .makeText(
                                                App.instance,
                                                MESSAGE.ERROR,
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                        Log.d(TAG, "MainScreen: examples load Error")
                                    }
                                }
                            })
                        RetrofitManager.instance.followFollowersUserId(
                            userInfo.userId,
                            completion = { responseState, resposeBody ->
                                when (responseState) {
                                    RESPONSE_STATE.OKAY -> {
                                        follower.clear()
                                        follower = resposeBody!!
                                        followingBool = true
                                        if (followerBool && followingBool) navController.navigate("${route}_follow_screen/FOLLOWER")
                                    }
                                    RESPONSE_STATE.FAIL -> {
                                        Toast
                                            .makeText(
                                                App.instance,
                                                MESSAGE.ERROR,
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                        Log.d(TAG, "MainScreen: examples load Error")
                                    }
                                }
                            })
                    }
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
                //productFrame(item, navController, route, is_me)// 수정필요
            }
        }
    }
}
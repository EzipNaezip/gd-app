package com.dongminpark.projectgd.Effects

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dongminpark.projectgd.App
import com.dongminpark.projectgd.Button.FollowButton
import com.dongminpark.projectgd.Button.ProfileEditButton
import com.dongminpark.projectgd.Model.Post
import com.dongminpark.projectgd.Model.User
import com.dongminpark.projectgd.Screens.ButtonFormat
import com.dongminpark.projectgd.Screens.ProfileEditScreen
import com.dongminpark.projectgd.Screens.TextFormat
import com.dongminpark.projectgd.Screens.WidthDivide
import com.dongminpark.projectgd.Utils.USER
import com.dongminpark.projectgd.ui.theme.suite


@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun UserMyContent(navController: NavController, route: String, bookmarkList: ArrayList<Post>, galleryList: ArrayList<Post>, userInfo: User) {
    var myScreenButtonIndex by rememberSaveable { mutableStateOf(0) }

    var buttons by remember { mutableStateOf(listOf("갤러리", "북마크")) }

    if (userInfo.isMe){
        USER.USERID = userInfo.userId
        USER.NAME = userInfo.name
        USER.NAMETEMP = userInfo.name
        USER.DESCRIPTION = userInfo.description
        USER.DESCRIPTION_TEMP = userInfo.description
        USER.PROFILEIMGURL = userInfo.profileImgUrl
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
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
                    ProfileImage(100, userInfo.profileImgUrl)
                }

                userName(name = "${userInfo.name}")

                userReadme(readme = "${userInfo.description}")

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .padding(top = 8.dp)
                        .padding(start = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    TextFormat(NumberContraction(userInfo.postCount), "Gallery")
                    ButtonFormat(
                        NumberContraction(userInfo.followCount),
                        "Following",
                        userInfo,
                        navController,
                        route
                    )
                    ButtonFormat(
                        NumberContraction(userInfo.followerCount),
                        "Follower",
                        userInfo,
                        navController,
                        route
                    )
                }

                // 프로필 수정 버튼 or Follow 버튼
                if (userInfo.isMe) {
                    ProfileEditButton {
                        //profileEditScreenState = "프로필"
                        Toast.makeText(App.instance, "현재 지원하지 않는 기능입니다 \n웹페이지에서 수정하실 수 있습니다!!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    FollowButton(true, userInfo.userId, userInfo.isMe)
                }


                // 가로선
                WidthDivide()
            }

            //ALL, 갤러리, 북마크 버튼
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 0.dp, end = 0.dp, bottom = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                ProductButton(onClick = {myScreenButtonIndex = 0}, myScreenButtonIndex, 0, "갤러리")
                Spacer(modifier = Modifier.width(10.dp))
                ProductButton(onClick = {myScreenButtonIndex = 1}, myScreenButtonIndex, 1, "북마크")
            }
        }

        val bookmarkRows = bookmarkList.chunked(2)
        val galleryRows = galleryList.chunked(2)

        var rows = if (myScreenButtonIndex == 0) galleryRows else bookmarkRows

        items(rows) { rowItems ->
            Row(Modifier.fillMaxWidth()) {
                rowItems.forEach { product ->
                    Column(Modifier.weight(1f)) {
                        if (myScreenButtonIndex == 0) productFrame(
                            product,
                            navController,
                            route,
                            product.bookmark,
                            product.me,
                            userInfo.isMe
                        )
                        else productFrame(product, navController, route, product.bookmark, product.me, userInfo.isMe)
                    }
                }
            }
        }
    }

    if (userInfo.isMe) {
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
}

@Composable
fun ProductButton(onClick: () -> Unit, myScreenButtonIndex: Int, index: Int, label: String){
    OutlinedButton(
        modifier = if(index == 0) Modifier.fillMaxWidth(0.5f) else Modifier.fillMaxWidth(),
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (myScreenButtonIndex == index)
                MaterialTheme.colors.primaryVariant else Color.White
        ),
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
}

@Composable
fun userName(name: String) {
    Text(
        text = name,
        fontFamily = suite,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp,
        color = MaterialTheme.colors.onPrimary,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun userReadme(readme: String) {
    Text(
        text = readme,
        fontFamily = suite,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        color = MaterialTheme.colors.secondary,
        modifier = Modifier
            .padding(vertical = 5.dp)
            .padding(start = 30.dp, end = 30.dp)
    )
}
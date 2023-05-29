package com.example.gd.Effects

import android.annotation.SuppressLint
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
import com.example.gd.Button.FollowButton
import com.example.gd.Button.ProfileEditButton
import com.example.gd.Screens.*
import com.example.gd.ui.theme.suite

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun UserMyContent(navController: NavController, route: String, userInfo: Info){
    var myScreenButtonIndex by rememberSaveable{ mutableStateOf(0) }
    val bookmarkList = arrayListOf<Product>()
    val galleryList = arrayListOf<Product>()

    var buttons by remember { mutableStateOf(listOf("갤러리", "북마크")) }

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
                    ProfileImage(100)
                }

                userName(name = "${userInfo.name}")

                userReadme(readme = "${userInfo.readme}")

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .padding(top = 8.dp)
                        .padding(start = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    TextFormat(NumberContraction(2), "Gallery")
                    ButtonFormat(NumberContraction(userInfo.following), "Following", navController, route)
                    ButtonFormat(NumberContraction(userInfo.follower), "Follower", navController, route)
                }

                // 프로필 수정 버튼 or Follow 버튼
                if(userInfo.isMe){
                    ProfileEditButton {
                        profileEditScreenState = "프로필"
                    }
                }else{
                    FollowButton(true)
                }


                // 가로선
                WidthDivide()
            }

            //ALL, 갤러리, 북마크 버튼
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 0.dp, end = 0.dp, bottom = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                item {
                    buttons.forEachIndexed { index, label ->
                        OutlinedButton(
                            modifier = Modifier.size(175.dp, 35.dp),
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

        addProduct(bookmarkList)
        addProduct(galleryList)

        val bookmarkRows = bookmarkList.chunked(2)
        val galleryRows = galleryList.chunked(2)

        var rows = if (myScreenButtonIndex == 0) galleryRows else bookmarkRows

        items(rows) { rowItems ->
            Row(Modifier.fillMaxWidth()) {
                rowItems.forEach { product ->
                    Column(Modifier.weight(1f)) {
                        if (myScreenButtonIndex == 0) productFrame(product, navController,  route, false)
                        else productFrame(product, navController, route, true)
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
fun userName(name: String){
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
fun userReadme(readme: String){
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
package com.example.gd.Screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gd.Effects.productFrame
import com.example.gd.R
import com.example.gd.ui.IconPack
import com.example.gd.ui.iconpack.Right
import com.example.gd.ui.theme.suite

@Composable
fun MyScreen(navController: NavHostController) {
    val USER = Info("박동민", "안녕하세요 저는 박동민입니다.\n 테스트 Readme입니다.", "pdm001125",120, 200, R.drawable.logo)
    val productList = arrayListOf<Product>()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            // 프로필 사진
            Image(
                painter = painterResource(id = USER.profilePicture),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.LightGray, CircleShape)
            )

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
                        color = MaterialTheme.colors.onPrimary,
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
                        color = MaterialTheme.colors.onPrimary,
                    )
                }
            }
            // follow 버튼
            Box(modifier = Modifier.fillMaxWidth(), Alignment.BottomEnd) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "프로필 수정")
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

        widthDivide()

        listView("갤러리", productList, navController, false)

        widthDivide()

        listView("북마크", productList, navController)

        // 버튼(좋아요모음        >)
        // LazyRow(컨텐츠 표시)
    }
}

class Info(
    val name: String, val readme: String, val ID: String,
    var follower: Int, var following: Int,
    @DrawableRes val profilePicture: Int,
)

@Composable
fun widthDivide(){
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
fun listView(name: String, productList: ArrayList<Product>, navController: NavHostController, isMine: Boolean = true){
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

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
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

        LazyRow{
            items(productList){item ->
                productFrame(item, navController, isMine)
            }
        }
    }
}
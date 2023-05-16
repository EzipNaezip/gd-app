package com.example.gd.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.navigation.NavController
import com.example.gd.Button.FollowButton
import com.example.gd.R
import com.example.gd.ui.IconPack
import com.example.gd.ui.iconpack.Left
import com.example.gd.ui.theme.suite

@Composable
fun UserScreen(navController: NavController, route: String) {
    val USER: Info
    val productList = arrayListOf<Product>()

    USER = Info("박동민", "안녕하세요 저는 박동민입니다.\n 테스트 Readme입니다.", "pdm001125", 120, 200, R.drawable.logo)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            imageVector = IconPack.Left,
            contentDescription = "Back",
            modifier = Modifier
                .clickable {
                    navController.popBackStack()
                }
                .width(30.dp)
                .height(30.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            // 프로필 사진
            Image(
                painter = painterResource(id = PRODUCT.profilePicture),
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
                    text = PRODUCT.name,
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

            // Follow & Unfollow Button
            Box(modifier = Modifier.fillMaxWidth(), Alignment.BottomEnd) {
                FollowButton()
            }

        }
        // 가로선

        // Readme
        Text(
            text = "${PRODUCT.info}",
            fontFamily = suite,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        WidthDivide()

        ListView("갤러리", productList, navController, route, false)

        WidthDivide()

        ListView("북마크", productList, navController, route)
    }
}
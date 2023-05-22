package com.example.gd.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gd.Button.FollowButton
import com.example.gd.Effects.ProfileImage
import com.example.gd.Effects.TopAppBarScreenFormat
import com.example.gd.R
import com.example.gd.ui.theme.Black
import com.example.gd.ui.theme.suite

var currentPage by mutableStateOf(1)

val followingList = arrayListOf("User A", "User B", "User C", "User C", "User A", "User B", "User C", "User C", "User A", "User B", "User C", "User C")
val followerList = arrayListOf("User X", "User Y", "User Z")


@Preview
@Composable
fun FollowScreen(name: String = "Test name") {
    TopAppBarScreenFormat(
        titleText = name,
        IsLeftButton = true,
        IsRightButton = false,
        content = { FollowScreenContent() },
        leftButtonClick = { /* */ },
        rightButtonClick = {}
    )
}

@Composable
fun FollowScreenContent() {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = { currentPage = 1 },
                    shape = RectangleShape,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary)
                ) {
                    Text(
                        text = "Following",
                        fontFamily = suite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = if (currentPage == 1)
                            MaterialTheme.colors.onPrimary
                        else MaterialTheme.colors.secondary
                    )
                }
                Divider(
                    color = if (currentPage == 1)
                        MaterialTheme.colors.onPrimary
                    else MaterialTheme.colors.secondaryVariant,
                    thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = { currentPage = 2 },
                    shape = RectangleShape,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary)
                ) {
                    Text(
                        text = "Follower",
                        fontFamily = suite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = if (currentPage == 2)
                            MaterialTheme.colors.onPrimary
                        else MaterialTheme.colors.secondary
                    )
                }
                Divider(
                    color = if (currentPage == 2)
                        MaterialTheme.colors.onPrimary
                    else MaterialTheme.colors.secondaryVariant,
                    thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                when (currentPage) {
                    1 -> AddFollowing()
                    2 -> AddFollower()
                }
            }
        }
    }
}

@Composable
fun AddFollowing() {
    followingList.forEach { name ->
        FollowingPage(name)
    }
}

@Composable
fun FollowingPage(name: String) {
    Column(verticalArrangement = Arrangement.Center) {
        Row(
            modifier = Modifier
                .clickable {
                    // 프로필 창으로 이동
                }
        ) {
            ProfileImage(ImageSize = 70)
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            Text(
                text = name,
                color = Black, //MaterialTheme.colors.onPrimary
                fontFamily = suite,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
            Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                FollowButton()
            }
        }
        Divider()
    }
}

@Composable
fun FollowerPage(name: String) {
    Column {
        Row(
            modifier = Modifier
                .clickable {
                    // 프로필 창으로 이동
                }
        ) {
            ProfileImage(ImageSize = 70)
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            Text(
                text = name,
                color = Black, //MaterialTheme.colors.onPrimary
                fontFamily = suite,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Divider()
    }
}
@Composable
fun AddFollower() {
    followerList.forEach { name ->
        FollowerPage(name)
    }
}


@Composable
fun Divider() {
    Divider(
        color = MaterialTheme.colors.secondaryVariant,
        thickness = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

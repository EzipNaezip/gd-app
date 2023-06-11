package com.dongminpark.projectgd.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dongminpark.projectgd.Button.FollowButton
import com.dongminpark.projectgd.Effects.ProfileImage
import com.dongminpark.projectgd.Effects.TopAppBarScreenFormat
import com.dongminpark.projectgd.Model.FollowUser
import com.dongminpark.projectgd.ui.theme.Black
import com.dongminpark.projectgd.ui.theme.suite

//var currentPage by mutableStateOf(1)

//val followingList = arrayListOf("User A", "User B", "User C", "User C", "User A", "User B", "User C", "User C", "User A", "User B", "User C", "User C")
//val followerList = arrayListOf("User X", "User Y", "User Z")

val FOLLOWING = "FOLLOWING"
val FOLLOWER = "FOLLOWER"

@Composable
fun FollowScreen(
    navController: NavController, route: String,
    name: String = "목록", currentPage: String
) {
    TopAppBarScreenFormat(
        titleText = name,
        IsLeftButton = true,
        IsRightButton = false,
        content = { FollowScreenContent(navController, route, currentPage) },
        leftButtonClick = { navController.popBackStack() },
        rightButtonClick = {}
    )
}

@Composable
fun FollowScreenContent(navController: NavController, route: String, currentPage: String) {
    var currentPageTemp by rememberSaveable { mutableStateOf(currentPage) }
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
                    onClick = { currentPageTemp = FOLLOWING },
                    shape = RectangleShape,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary)
                ) {
                    Text(
                        text = "${following.size} Following",
                        fontFamily = suite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = if (currentPageTemp == FOLLOWING)
                            MaterialTheme.colors.onPrimary
                        else MaterialTheme.colors.secondary
                    )
                }
                Divider(
                    color = if (currentPageTemp == FOLLOWING)
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
                    onClick = { currentPageTemp = FOLLOWER },
                    shape = RectangleShape,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary)
                ) {
                    Text(
                        text = "${follower.size} Follower",
                        fontFamily = suite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = if (currentPageTemp == FOLLOWER)
                            MaterialTheme.colors.onPrimary
                        else MaterialTheme.colors.secondary
                    )
                }
                Divider(
                    color = if (currentPageTemp == FOLLOWER)
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
                when (currentPageTemp) {
                    FOLLOWING -> AddFollowing(navController, route)
                    FOLLOWER -> AddFollower(navController, route)
                }
            }
        }
    }
}

@Composable
fun AddFollowing(navController: NavController, route: String) {
    following.forEach { name ->
        FollowingPage(navController, route, name)
    }
}

@Composable
fun FollowingPage(navController: NavController, route: String, user: FollowUser) {
    Column(verticalArrangement = Arrangement.Center) {
        Row(
            modifier = Modifier
                .clickable {
                    navController.navigate(route + "_user_screen/${user.userId}")
                }
        ) {
            ProfileImage(ImageSize = 70, user.profileImgUrl)
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            Text(
                text = user.name,
                color = Black, //MaterialTheme.colors.onPrimary
                fontFamily = suite,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )

            Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                FollowButton(followingId = user.userId, isFollow = user.follow)
            }

        }
        Divider()
    }
}

@Composable
fun FollowerPage(navController: NavController, route: String, user: FollowUser) {
    Column(verticalArrangement = Arrangement.Center)  {
        Row(
            modifier = Modifier
                .clickable {
                    navController.navigate(route + "_user_screen/${user.userId}")
                }
        ) {
            ProfileImage(ImageSize = 70, user.profileImgUrl)
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            Text(
                text = user.name,
                color = Black, //MaterialTheme.colors.onPrimary
                fontFamily = suite,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )

            Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                FollowButton(followingId = user.userId, isFollow = user.follow)
            }
        }
        Divider()
    }
}
@Composable
fun AddFollower(navController: NavController, route: String) {
    follower.forEach { name ->
        FollowerPage(navController, route, name)
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

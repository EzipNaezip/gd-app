package com.example.gd.Screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gd.Button.BackButton
import com.example.gd.Button.BookmarkButton
import com.example.gd.Button.FavoriteButton
import com.example.gd.Button.FollowButton
import com.example.gd.Effects.*
import com.example.gd.ui.theme.SearchBarBD
import com.example.gd.ui.theme.suite

@Composable
fun DetailScreen(navController: NavController, route: String) {
    LazyColumn() {
        item {
            // 사진 Pager로 표시 및 현재 페이지 표시
            Box(contentAlignment = Alignment.TopStart) {
                PostUi(images = PRODUCT.imageId)
                BackButton(navController = navController)
            }

            // 프로필 내용
            Row() {
                UserProfileName(navController = navController, route = route)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp), contentAlignment = Alignment.BottomEnd
                ) {
                    FollowButton()
                }
            }

            // 내용
            PostContent()

            // 태그
            TagList(tags = PRODUCT.tags)

            // 버튼과 생성일자
            Row(modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp)) {
                Column {
                    FavoriteCount()
                    Row {
                        FavoriteButton()
                        BookmarkButton()
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    CreationDate()
                }
            }

            // 댓글 구분선
            Divider(
                color = MaterialTheme.colors.secondaryVariant,
                modifier = Modifier
                    .height(1.dp)
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth()
            )

            // 댓글 수
            CommentCount()

            // 댓글 목록
            CommentsList(navController, route)
        }
    }
}


@Composable
fun UserProfileName(navController: NavController, route: String) {
    Row(
        modifier = Modifier
            .clickable {
                // 프로필 창으로 이동
                navController.navigate(route + "_user_screen")
            }
            .padding(vertical = 8.dp, horizontal = 15.dp),
    ) {
        ProfileImage(ImageSize = 50)
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = "${PRODUCT.name}",
            modifier = Modifier.padding(vertical = 13.dp),
            color = Color.Black,
            fontFamily = suite,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        )
    }
}

@Composable
fun CreationDate() {
    Text(
        text = PRODUCT.date.slice(0..10),
        color = MaterialTheme.colors.onPrimary,
        fontFamily = suite,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp
    )
}

@Composable
fun FavoriteCount() {
    Text(
        text = "좋아요 7개",
        color = MaterialTheme.colors.onPrimary,
        fontFamily = suite,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp
    )
}

@Composable
fun CommentCount() {
    Text(
        text = "댓글 7개",
        color = MaterialTheme.colors.onPrimary,
        fontFamily = suite,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp)
    )
}

@Composable
fun CommentsList(navController: NavController, route: String) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    // 댓글 리스트를 매개변수로 받아서 for문 사용
    Column() {
        for (i in 0..8) {
            if (!expanded && i == 5) {
                Row(Modifier.clickable { expanded = true }) {
                    Text(
                        text = "댓글 전체보기",
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp),
                        color = Color.Black,
                        fontFamily = suite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )
                }
                break
            }
            Comments(navController = navController, route = route)
        }
        // 댓글 달기 창
        CommentInputBar(
            onSearch = {
                // 댓글달기 API 호출
            }
        )
    }
}

@Preview
@Composable
fun Comments(navController: NavController, route: String) {
    Row(
        modifier = Modifier
            .clickable {
                // 프로필 창으로 이동
                navController.navigate(route + "_user_screen")
            }
            .padding(vertical = 8.dp, horizontal = 15.dp),
    ) {
        ProfileImage(ImageSize = 40)
        Spacer(modifier = Modifier.width(5.dp))
        Column() {
            Text(
                text = "${PRODUCT.name}",
                modifier = Modifier.padding(vertical = 1.dp),
                color = Color.Black,
                fontFamily = suite,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )
            Text(
                text = "${PRODUCT.info}",
                modifier = Modifier.padding(vertical = 1.dp),
                color = Color.Black,
                fontFamily = suite,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CommentInputBar(onSearch: (String) -> Unit){
    var searchText by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column() {
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = {
                Text(
                    color = MaterialTheme.colors.secondaryVariant,
                    text = "댓글을 입력해 주세요"
                )
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(62.dp)
                .padding(horizontal = 15.dp, vertical = 5.dp)
                .border(
                    width = 1.dp,
                    color = SearchBarBD,
                    shape = RoundedCornerShape(8.dp)
                ),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    search(searchText, onSearch, focusManager, keyboardController)
                }),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                disabledTextColor = Color.Black,
                backgroundColor = MaterialTheme.colors.onSecondary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            trailingIcon = {
                Row {
                    if (searchText != "") {
                        Icon(
                            Icons.Default.Clear,
                            contentDescription = "clear text",
                            modifier = Modifier
                                .clickable {
                                    searchText = ""
                                }
                                .padding(vertical = 15.dp)
                        )
                    }

                    Divider(
                        color = SearchBarBD,
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                    )

                    TextButton(
                        onClick = {
                            search(searchText, onSearch, focusManager, keyboardController)
                        },
                    ) {
                        Text(
                            text = "등록",
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }
            }
        )
    }
}
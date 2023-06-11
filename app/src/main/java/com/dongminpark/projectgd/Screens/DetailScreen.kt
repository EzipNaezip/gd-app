package com.dongminpark.projectgd.Screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dongminpark.projectgd.App
import com.dongminpark.projectgd.Button.*
import com.dongminpark.projectgd.Effects.*
import com.dongminpark.projectgd.Model.Comments
import com.dongminpark.projectgd.Model.PostPostnum
import com.dongminpark.projectgd.Retrofit.RetrofitManager
import com.dongminpark.projectgd.ui.theme.SearchBarBD
import com.dongminpark.projectgd.ui.theme.suite
import com.dongminpark.projectgd.Utils.Constants.TAG
import com.dongminpark.projectgd.Utils.MESSAGE
import com.dongminpark.projectgd.Utils.RESPONSE_STATE


var postPostnum: PostPostnum = PostPostnum()
var commentList = SnapshotStateList<Comments>()

@Composable
fun DetailScreen(navController: NavController, route: String, postNum: Int) {
    var start by rememberSaveable { mutableStateOf(0) }
    var display by rememberSaveable { mutableStateOf(20) }
    //var postPostnum by remember { mutableStateOf(PostPostnum()) } // 세이버블이 아니라서 창 나갔다 돌아오면 사라짐
    var loadPostPostnum = true//by rememberSaveable { mutableStateOf(true) }
    var show by rememberSaveable { mutableStateOf(false) }
    var expanded by rememberSaveable { mutableStateOf(false) }
    val commentScrollState = rememberLazyListState() // 무한 스크롤 구현용


    // 정보 가져오는 API
    if (loadPostPostnum) {
        loadPostPostnum = false
        commentList.clear()
        RetrofitManager.instance.postListPostnum(
            postNum,
            completion = { responseState, responseBody ->
                when (responseState) {
                    RESPONSE_STATE.OKAY -> {
                        postPostnum = responseBody!!
                        Log.e(TAG, "DetailScreen: ${postPostnum.likesCount}", )
                        show = true
                        Log.d(TAG, "api 호출 성공")
                    }
                    RESPONSE_STATE.FAIL -> {
                        Toast.makeText(App.instance, MESSAGE.ERROR, Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "api 호출 에러")
                    }
                }
            })
        contentsGet(postNum, start, display)
    }



    if (show) {
        LazyColumn(
            state = commentScrollState,
            modifier = Modifier
                .padding(bottom = 16.dp)
        ) {
            item {
                // 사진 Pager로 표시 및 현재 페이지 표시
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.TopStart
                ) {
                    PostUi(images = postPostnum.path)
                    BackButton(navController = navController)
                }

                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    // 프로필 내용
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        UserProfileName(
                            navController = navController,
                            route = route,
                            userId = postPostnum.userId,
                            profileImgUrl = postPostnum.profileImgUrl,
                            postPostnum.name
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            contentAlignment = Alignment.BottomEnd
                        ) {
                            if (!postPostnum.isMe) FollowButton(postPostnum.follow,postPostnum.userId, postPostnum.isMe)
                        }
                    }
                    Spacer(modifier = Modifier.padding(vertical = 8.dp))
                    // 내용
                    PostContent(postPostnum.description)

                    // 태그
                    TagList(tags = postPostnum.tagIds)

                    // 버튼과 생성일자
                    Row(modifier = Modifier.padding(vertical = 8.dp)) {
                        Column {
                            FavoriteCount(postPostnum.likesCount)
                            Spacer(modifier = Modifier.padding(vertical = 8.dp))
                            Row {
                                FavoriteButton(postPostnum.like, postPostnum.postNum)
                                if (!postPostnum.isMe) BookmarkButton(postPostnum.bookmark, postPostnum.postNum)
                                else SaveButton(postNum, saved = true, onClick = {
                                    // 동글뱅이 돌아가는 모양
                                })
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 35.dp),
                            contentAlignment = Alignment.BottomEnd
                        ) {
                            CreationDate(postPostnum.timestamp)
                        }
                    }

                    // 댓글 구분선
                    Spacer(modifier = Modifier.padding(vertical = 8.dp))
                    Divider(
                        color = MaterialTheme.colors.secondaryVariant,
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.padding(vertical = 8.dp))

                    // 댓글 수
                    CommentCount(commentList.size)

                    // 댓글 입력창
                    CommentInputBar(
                        onSearch = {
                            // 댓글달기 API 호출
                            RetrofitManager.instance.comments(
                                content = it,
                                postNum = postNum,
                                completion = { responseState ->
                                    when (responseState) {
                                        RESPONSE_STATE.OKAY -> {
                                            commentList.clear()
                                            start = 0
                                            display = 20
                                            contentsGet(postNum, start, display)
                                            start += 20
                                            display += 20
                                            Log.d(TAG, "api 호출 성공")
                                        }
                                        RESPONSE_STATE.FAIL -> {
                                            Toast.makeText(
                                                App.instance,
                                                MESSAGE.ERROR,
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            Log.d(TAG, "api 호출 에러")
                                        }
                                    }
                                })
                        }
                    )
                }
            }
            // 댓글 목록
            items(
                if (expanded) commentList.size
                else if (commentList.size < 5) commentList.size else 5
            ) {
                Comments(
                    navController = navController,
                    route = route,
                    comment = commentList[it],
                    postNum = postNum
                )

                if (!expanded && it == 4 && commentList.size > 5) {
                    Row(
                        Modifier
                            .padding(horizontal = 16.dp)
                            .clickable {
                                expanded = true
                            }) {
                        Text(
                            text = "댓글 전체보기",
                            modifier = Modifier.padding(vertical = 8.dp),
                            color = Color.Black,
                            fontFamily = suite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp
                        )
                    }
                }
            }

            // 댓글 무한 스크롤 // 현재 길이랑 맥시멈이랑 비교해서 같으면 실행되도록 해야함.
            if (commentScrollState.isScrollInProgress && (commentScrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index!! >= commentList.size - 1)) {
                //contentsGet(postNum, start, display)
            }
        }
    }
}

fun contentsGet(postNum: Int, start: Int, display: Int) {
    RetrofitManager.instance.commentsListPostNum(
        postNum,
        start,
        display,
        completion = { responseState, responseBody ->
            when (responseState) {
                RESPONSE_STATE.OKAY -> {
                    commentList.addAll(responseBody!!)
                    Log.d(TAG, "api 호출 성공")
                }
                RESPONSE_STATE.FAIL -> {
                    Toast.makeText(App.instance, MESSAGE.ERROR, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "api 호출 에러")
                }
            }
        })
}


@Composable
fun UserProfileName(
    navController: NavController,
    route: String,
    userId: Int,
    profileImgUrl: String,
    name: String
) {
    Row(
        modifier = Modifier
            .clickable {
                // 프로필 창으로 이동
                navController.navigate(route + "_user_screen" + "/$userId")
            }
            .padding(vertical = 8.dp),
    ) {
        ProfileImage(ImageSize = 50, profileImgUrl)
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = name,
            modifier = Modifier.padding(vertical = 12.dp),
            color = Color.Black,
            fontFamily = suite,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        )
    }
}

@Composable
fun CreationDate(date: String) {

    Text(
        text = date,//PRODUCT.date.slice(0..10),
        color = MaterialTheme.colors.onPrimary,
        fontFamily = suite,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp
    )
}

@Composable
fun FavoriteCount(likesCount: Int) {
    Text(
        text = "좋아요 ${likesCount}개",
        color = MaterialTheme.colors.onPrimary,
        fontFamily = suite,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp
    )
}

@Composable
fun CommentCount(commentCount: Int) {
    Text(
        text = "댓글 ${NumberContraction(commentCount)}개",
        color = MaterialTheme.colors.onPrimary,
        fontFamily = suite,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Comments(navController: NavController, route: String, comment: Comments, postNum: Int) {
    var updateComment by rememberSaveable { mutableStateOf(false) }
    var searchText by rememberSaveable { mutableStateOf("") }

    Row(
        modifier = Modifier
            .clickable {
                // 프로필 창으로 이동
                navController.navigate(route + "_user_screen/${comment.userId}")
            }
            .padding(vertical = 10.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        ProfileImage(ImageSize = 40, comment.profileImgUrl)
        Spacer(modifier = Modifier.width(8.dp))
        Column(verticalArrangement = Arrangement.Center) {
            textFormat(str = "${comment.name}")
            if (updateComment) {
                val focusManager = LocalFocusManager.current
                val keyboardController = LocalSoftwareKeyboardController.current

                TextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    placeholder = {
                        textFormat(str = "공백은 안돼요!!", color = MaterialTheme.colors.secondaryVariant)
                    },
                    textStyle = TextStyle(
                        fontFamily = suite,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(0.dp)
                        .background(color = Color.Transparent),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        disabledTextColor = Color.Black,
                        backgroundColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            if (searchText.isNotEmpty()) {
                                // 수정 api
                            }
                            focusManager.clearFocus()
                            keyboardController?.hide()
                        }),
                    trailingIcon = {
                        Row (verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ){
                            TextButton(
                                onClick = {
                                    if (searchText.isNotEmpty()) {
                                        RetrofitManager.instance.commentsSerialNumUpdate(comment.serialNum, searchText,completion = { responseState ->
                                            when (responseState) {
                                                RESPONSE_STATE.OKAY -> {
                                                    commentList.clear()
                                                    contentsGet(postNum, 0, 20)
                                                    Log.d(TAG, "api 호출 성공")
                                                }
                                                RESPONSE_STATE.FAIL -> {
                                                    Toast.makeText(App.instance, MESSAGE.ERROR, Toast.LENGTH_SHORT).show()
                                                    Log.d(TAG, "api 호출 에러")
                                                }
                                            }
                                        })
                                    }
                                    updateComment = false
                                },
                            ) {
                                textFormat("확인")
                            }

                            textFormat("/")

                            TextButton(
                                onClick = {
                                    updateComment = false
                                },
                            ) {
                                textFormat("취소")
                            }
                        }
                    }
                )
            } else {
                textFormat(str = "${comment.content}")
            }
        }
        if (comment.isMe) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Row() {
                    textFormat(str = "수정", modifier = Modifier.clickable {
                        // 입력창을 띄워줘야함. 입력 받은 값이 onClick되면 밑에를 실행시켜야함. 어우 귀찮아
                        updateComment = true
                        searchText = comment.content
                    })

                    textFormat("/")

                    textFormat(str = "삭제", color = Color.Red, modifier = Modifier.clickable {
                        RetrofitManager.instance.commentsSerialNumDelete(
                            comment.serialNum,
                            completion = { responseState ->
                                when (responseState) {
                                    RESPONSE_STATE.OKAY -> {
                                        commentList.clear()
                                        contentsGet(postNum, 0, 20)
                                        Log.d(TAG, "api 호출 성공")
                                    }
                                    RESPONSE_STATE.FAIL -> {
                                        Toast.makeText(
                                            App.instance,
                                            MESSAGE.ERROR,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        Log.d(TAG, "api 호출 에러")
                                    }
                                }
                            })
                    })
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CommentInputBar(onSearch: (String) -> Unit) {
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
                .padding(vertical = 8.dp)
                .border(
                    width = 1.dp,
                    color = SearchBarBD,
                    shape = RoundedCornerShape(8.dp)
                ),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    search(searchText, onSearch, focusManager, keyboardController)
                    searchText = ""
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
                        ClearTextButton {
                            searchText = ""
                        }
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
                            searchText = ""
                        }
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

@Composable
fun textFormat(str: String, color: Color = Color.Black, modifier: Modifier = Modifier) {
    Text(
        text = str,
        color = color,
        fontFamily = suite,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        modifier = modifier.padding(horizontal = 5.dp)
    )
}
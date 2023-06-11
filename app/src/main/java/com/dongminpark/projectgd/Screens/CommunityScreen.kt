package com.dongminpark.projectgd.Screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dongminpark.projectgd.App
import com.dongminpark.projectgd.Effects.CommunitySearchBar
import com.dongminpark.projectgd.Effects.productFrame
import com.dongminpark.projectgd.Model.Post
import com.dongminpark.projectgd.R
import com.dongminpark.projectgd.Retrofit.RetrofitManager
import com.dongminpark.projectgd.ui.theme.suite
import com.dongminpark.projectgd.Utils.Constants.TAG
import com.dongminpark.projectgd.Utils.List.TAGLIST
import com.dongminpark.projectgd.Utils.MESSAGE
import com.dongminpark.projectgd.Utils.RESPONSE_STATE

val postList = SnapshotStateList<Post>()
var selectedButtonIndex = 0
@Composable
fun ComunityScreen(navController: NavController) {
    var start by rememberSaveable { mutableStateOf(0) }
    var display by rememberSaveable { mutableStateOf(20) }
    var selectedButtonIndex2 by rememberSaveable { mutableStateOf(selectedButtonIndex) }
    //val postList = remember { mutableStateListOf<Post>() } // 얘도 세이버블 아니라서사라짐
    var loadPost by rememberSaveable { mutableStateOf(true) }

    var searchState by rememberSaveable { mutableStateOf(true) } // 검색 전후 구분
    val scrollState = rememberLazyGridState() // 무한 스크롤 구현용

    var buttons by remember { mutableStateOf(TAGLIST) }

    var label by rememberSaveable { mutableStateOf("TOP 30") }

    // Api를 호출해서 값을 불러오는 코드를 넣는 자리.
    if (postList.size == 0 && loadPost) {
        postList.clear()
        loadPost = false
        postpopular(start, display, postList)
        start += 20
        display += 20
    }

        // 검색창 + 검색 옵션
        Column(
            modifier = Modifier
                .fillMaxSize()
                .border(0.dp, Color.Transparent),
            verticalArrangement = Arrangement.Top
        ) {
            CommunitySearchBar(onSearch = {
                loadPost = false
                postList.clear()
                start = 0
                display = 20
                searchState = false
                // 검색 api
                postSearch(start, display, it, postList)
                start += 20
                display += 20
            })

            if (searchState) {
                LazyRow(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp),
                    horizontalArrangement = Arrangement.Start,
                ) {
                    item {
                        buttons.forEachIndexed { index, label ->
                            OutlinedButton(
                                modifier = Modifier.size(
                                    (label.length * 13 + 50).dp,
                                    height = 35.dp
                                ),
                                onClick = {
                                    loadPost = false
                                    postList.clear()
                                    start = 0
                                    display = 20
                                    selectedButtonIndex = index
                                    selectedButtonIndex2 = index
                                    if (label == buttons[0]) {
                                        postpopular(start, display, postList)
                                    } else postfilter(label, start, display, postList)
                                    start += 20
                                    display += 20
                                },
                                colors = ButtonDefaults.buttonColors(backgroundColor = if (selectedButtonIndex2 == index) MaterialTheme.colors.primaryVariant else Color.White),
                                shape = RoundedCornerShape(30),
                                content = {
                                    Text(
                                        text = label,
                                        fontFamily = suite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 12.sp,
                                        color = if (selectedButtonIndex == index) Color.White else Color.Black
                                    )
                                }
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                        }
                    }
                }
            }

            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp)
                    .padding(top = 5.dp),
                columns = GridCells.Fixed(2),
                state = scrollState
            ) {
                items(postList) { post ->
                    productFrame(post, navController, "community", post.bookmark, post.me, post.me)
                }

                if (scrollState.isScrollInProgress && (scrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == postList.size - 1) && loadPost && postList.size == display) {
                    loadPost = false
                    if (!searchState){

                    }else {
                        if (label == buttons[0]) {
                            postpopular(start, display, postList)
                        } else postfilter(label, start, display, postList)
                    }
                    start += 20
                    display += 20
                }
            }
        }

}

fun postfilter(tagName: String, start: Int, display: Int, postList: SnapshotStateList<Post>) {
    RetrofitManager.instance.postFilter(
        tagName,
        start,
        display,
        completion = { responseState, respnseBody ->

            when (responseState) {
                RESPONSE_STATE.OKAY -> {
                    postList.addAll(respnseBody ?: arrayListOf())
                }
                RESPONSE_STATE.FAIL -> {
                    Toast.makeText(App.instance, MESSAGE.ERROR, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "api 호출 에러 : $respnseBody")
                }
            }
        })
}

fun postpopular(start: Int, display: Int, postList: SnapshotStateList<Post>) {
    RetrofitManager.instance.postPopular(
        start,
        display,
        completion = { responseState, respnseBody ->

            when (responseState) {
                RESPONSE_STATE.OKAY -> {
                    postList.addAll(respnseBody ?: arrayListOf())
                }
                RESPONSE_STATE.FAIL -> {
                    Toast.makeText(App.instance, MESSAGE.ERROR, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "api 호출 에러 : $respnseBody")
                }
            }
        })
}

fun postSearch(start: Int, display: Int, postNum: String, postList: SnapshotStateList<Post>){
    RetrofitManager.instance.postSearch(
        start, display, postNum, completion = { responseState, respnseBody ->
            when (responseState) {
                RESPONSE_STATE.OKAY -> {
                    if (respnseBody != null) {
                        postList.addAll(respnseBody)
                    }
                    Log.d(TAG, "api 호출 성공 : $respnseBody")
                }
                RESPONSE_STATE.FAIL -> {
                    Toast.makeText(App.instance, MESSAGE.ERROR, Toast.LENGTH_SHORT)
                        .show()
                    Log.d(TAG, "api 호출 에러 : $respnseBody")
                }
            }
        })
}

fun addProduct(productList: ArrayList<Product>) {
    // api로 값을 받아와서 원래 배열에 추가.
    repeat(20) {
        val temp = Product(
            "User Name $it",
            "안녕하세요",
            "2023-04-14 21:45:51",
            listOf("모던", "엘레강스", "고딕"),
            R.drawable.logo,
            listOf(R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo),
            false
        )

        productList.add(temp)
    }
}
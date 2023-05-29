package com.example.gd.Screens

import android.graphics.ImageFormat
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.gd.Effects.*
import com.example.gd.ui.theme.suite
import kotlinx.coroutines.delay

@Composable
fun MainScreen() {
    var state by rememberSaveable { mutableStateOf(0) } // 검색 상태 state
    var scrollState = rememberLazyGridState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        when(state){
            0 -> { // 검색 전
                MainSpacer(scrollState = scrollState)
                TextMessage(state)
                SearchBar( // 검색창
                    onSearch = {
                        state = 1
                        // 검색어로 검색한 결과 나타내는 코드 예정 => Api 호출
                    }
                )
                MainSpacer(scrollState = scrollState)

                ExampleItems(scrollState)
            }
            1 ->{ // 검색 중
                MainSpacer(scrollState = scrollState)
                TextMessage(state)
                SearchBarShimmer(onClick = {
                    // stop generate api 호출
                    state = 0
                })
                MainSpacer(scrollState = scrollState)

                ExampleItems(scrollState)
                LaunchedEffect(true) {
                    delay(1500)
                    state = 2
                }
            }
            2 -> { // 검색 후
                TextMessage(state)
                SearchBar( // 검색창
                    onSearch = {
                        state = 1
                        // 검색어로 검색한 결과 나타내는 코드 예정 => Api 호출
                    }
                )
                SearchResult()
                MainSpacer(scrollState = scrollState)
            }
        }
    }
}

@Composable
fun MainSpacer(scrollState: LazyGridState){
    var scrollOffset = scrollState.firstVisibleItemScrollOffset.dp/6
    if (scrollState.firstVisibleItemIndex == 0) {
        if (80.dp > scrollOffset)
            Spacer(modifier = Modifier.padding(80.dp - scrollOffset))
    }
}

@Composable
fun ExampleItems(scrollState: LazyGridState){
    // itemList를 받아와야함.
    var showDialog by rememberSaveable{ mutableStateOf(false) }


    LazyVerticalGrid(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .padding(top = 8.dp),
        columns = GridCells.Fixed(2),
        state = scrollState
    ) {
        items(20) {
            ModalFrame(EXITEM, onClick = {
                showDialog = true
            })
        }
    }

    if (showDialog){
        PopUpModal(EXITEM, showDialog, onDismiss = {
            showDialog = false
        })
    }
}

@Composable
fun TextMessage(num: Int) {
    val textList: List<String> =
        listOf("새로운 아이디어를 찾아보세요!", "디자이너가 열심히 그리는중...", "원하는 인테리어가 없으신가요? 말씀해주세요!")
    Text(
        text = textList[num],
        fontFamily = suite,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 18.sp,
        color = MaterialTheme.colors.onPrimary,
        modifier = Modifier.padding(20.dp).padding(top = 5.dp)
    )
}

@Composable
fun ModalFrame(exampleItem: MainExampleItem, onClick: () -> Unit) {
    // 매개변수로 해당 사진을 받아야함.
    // 사진을 띄우고 클릭하면 모달을 띄움
    Column(
        modifier = Modifier
            .fillMaxSize()
            .border(0.dp, Color.Transparent)
            .clickable {
                onClick()
            }
            .padding(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        ImageFormat(image = exampleItem.image)
    }
}

@Composable
fun PopUpModal(exampleItem: MainExampleItem, showDialog: Boolean, onDismiss: () -> Unit) {
    // 매개변수로 받은 객체의 이미지와 텍스트 출력
    if (showDialog) {
        Dialog(
            onDismissRequest = { onDismiss() }
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Button(onClick = { onDismiss() }) {
                    Column {
                        ImageFormat(exampleItem.image) // 이미지
                        PostContent(exampleItem.context) // 설명
                    }
                }
            }
        }
    }
}
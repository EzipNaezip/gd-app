package com.example.gd.Screens

import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gd.Button.BookmarkButton
import com.example.gd.Effects.*
import com.example.gd.MainActivity
import com.example.gd.R
import com.example.gd.ui.IconPack
import com.example.gd.ui.iconpack.Left
import com.example.gd.ui.theme.suite
import kotlinx.coroutines.delay
import java.util.Properties

@Composable
fun MainScreen(navController: NavController) {
    var waitTime = 0L
    BackHandler(enabled = true, onBack = {
//        if(System.currentTimeMillis() - waitTime >=1500 ) {
//            waitTime = System.currentTimeMillis()
//            Toast.makeText(navController.context,"한번 더 누르면 종료됩니다",Toast.LENGTH_SHORT).show()
//        } else {
//            System.exit(0)
//        }
    })

    var showDialog by rememberSaveable{ mutableStateOf(true) }
    val scrollState = rememberLazyGridState() // 무한 스크롤 구현용
    val productList = arrayListOf<Product>()

    addProduct(productList) // api로 수정해야함

    var state by rememberSaveable { mutableStateOf(0) }
    var searchState by rememberSaveable { mutableStateOf(true) }
    var isLoading by rememberSaveable { mutableStateOf(true) }

    // 테스트용 이미지. 추후 변경 예정. listOf()로 만들고, Api 호출전에 다시 초기화 하고, 값을 하나씩 넣는 방법 사용
    val testImage: List<Int> =
        listOf(R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        TextMessage(state)
        SearchBar( // 검색창
            onSearch = {
                searchState = false
                isLoading = true
                state = 1
                // 검색어로 검색한 결과 나타내는 코드 예정 => Api 호출
            },
        )

        // modalFrame이 클릭되면 아래와 같이 실행
//        showDialog = true
//        PopUpModal(showDialog, onDismiss = {
//            showDialog = false
//        })

/*
        when(state){
            1 -> Log.d("1state", "ㅎㅇ") // 검색창이 나오고 아래에 ModalFrame
            2 -> Log.d("2state", "ㅎㅇ") // 검색창이 로딩바로 바뀌고 아래에 ModalFrame
            3 -> Log.d("3state", "ㅎㅇ") // 로딩창이 검색창으로 바뀌고 아래에 결과물
        }

 */


        if (searchState) {
            LazyVerticalGrid(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp),
                columns = GridCells.Fixed(2),
                state = scrollState
            ) {
                items(productList) { product ->
                    productFrame(product, navController, "main")// 수정 필요
                }

                if (scrollState.isScrollInProgress && (scrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == productList.size - 1))
                    addProduct(productList) // 바닥 도착(생성된 모든 값을 탐색했을 경우) -> 새로 값 불러옴.
            }
        } else {
            LaunchedEffect(isLoading) {
                if (isLoading) {
                    delay(1500)
                    isLoading = false
                    state = 2
                }
            }
            if (isLoading) {
                LoadingShimmerEffect()
            } else { // API사용해서 값 가져오면 실행.
                SearchResult(testImage)
            }
        }

    }
}

@Preview
@Composable
fun TextMessage(num: Int) {
    val textList: List<String> =
        listOf("새로운 아이디어를 찾아보세요!", "디자이너가 열심히 그리는중...", "원하는 가구가 없으신가요? 말씀해주세요!")
    Text(
        text = textList[num],
        fontFamily = suite,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 18.sp,
        color = MaterialTheme.colors.onPrimary,
        modifier = Modifier.padding(10.dp)
    )
}

@Composable
fun ModalFrame(onClick: () -> Unit) {
    // 매개변수로 해당 사진을 받아야함.
    // 사진을 띄우고 클릭하면 모달을 띄움
    Column(
        modifier = Modifier
            .fillMaxSize()
            .border(0.dp, Color.Transparent)
            .clickable {
                onClick()
            }
            .padding(horizontal = 2.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            contentScale = ContentScale.Fit,
            painter = painterResource(R.drawable.logo),
            contentDescription = "Image",
            modifier = Modifier
                .size(180.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }
}

@Composable
fun PopUpModal(showDialog: Boolean, onDismiss: () -> Unit) {
    // 매개변수로 텍스트, 사진을 받아야함
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
                        Image(
                            contentScale = ContentScale.Fit,
                            painter = painterResource(R.drawable.logo),
                            contentDescription = "Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                        )
                        PostContent()
                    }
                }
            }
        }
    }
}
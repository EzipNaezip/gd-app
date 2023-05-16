package com.example.gd.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gd.Effects.*
import com.example.gd.R
import com.example.gd.ui.theme.suite
import kotlinx.coroutines.delay

@Composable
fun MainScreen(navController: NavController) {
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
            modifier = Modifier.fillMaxWidth()
        )

        // api를 통해서 아래에 넣을 값을 가져온 후 아래 코드 실행
        // api를 통해서 가져오는 동안 로딩창 실행

        // searchState가 false가 되면 로딩창 실행.
        // 로딩창 하는동안 api를 통해 값 가져옴
        // 값을 받으면 실행

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
package com.example.gd.Screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gd.Effects.SearchBar
import com.example.gd.Effects.productFrame
import com.example.gd.R
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay

@Composable
fun ComunityScreen(navController: NavHostController) {
    var searchState by remember { mutableStateOf(true) } // 검색 전후 구분
    val scrollState = rememberLazyGridState() // 무한 스크롤 구현용

    val productList = arrayListOf<Product>() // 더미데이터 리스트 -> 전역으로 관리해야함. 커뮤니티를 벗어나면 클리어되게 설정. 그게 아니라면 유지돼야함.

    // pull to refresh
    var refreshing by remember { mutableStateOf(false) }
    LaunchedEffect(refreshing) {
        if (refreshing) {
            delay(3000)
            refreshing = false
        }
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = refreshing),
        onRefresh = { refreshing = true },
    ) {
        // 검색창 + 검색 옵션
        Column(
            modifier = Modifier
                .fillMaxSize()
                .border(0.dp, Color.Transparent),
            verticalArrangement = Arrangement.Top
        ) {
            SearchBar(onSearch = { searchState = false })
            // Api를 호출해서 값을 불러오는 코드를 넣는 자리.
            addProduct(productList)

            LazyVerticalGrid(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp),
                columns = GridCells.Fixed(2),
                //verticalArrangement = Arrangement.spacedBy(8.dp),
                //horizontalArrangement = Arrangement.spacedBy(8.dp),
                state = scrollState
            ) {
                items(productList) { product ->
                    productFrame(product, navController)
                }

                if (scrollState.isScrollInProgress && (scrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == productList.size - 1))
                    addProduct(productList) // 바닥 도착(생성된 모든 값을 탐색했을 경우) -> 새로 값 불러옴.
            }
        }
    }
}

fun addProduct(productList: ArrayList<Product>) {
    // api로 값을 받아와서 원래 배열에 추가.
    repeat(20) {
        val temp = Product(
            "User Name $it",
            "안녕하세요",
            "2023-04-14 21:45:51",
            listOf("#1","#2","#3","#4","#5","#6","#7",),
            R.drawable.logo,
            listOf(R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo),
            false
        )

        productList.add(temp)
    }
}
package com.example.gd.Screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gd.Effects.CommunitySearchBar
import com.example.gd.Effects.SearchBar
import com.example.gd.Effects.productFrame
import com.example.gd.R
import com.example.gd.ui.theme.suite
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay

@Composable
fun ComunityScreen(navController: NavHostController) {
    var searchState by remember { mutableStateOf(true) } // 검색 전후 구분
    val scrollState = rememberLazyGridState() // 무한 스크롤 구현용

    val productList = arrayListOf<Product>() // 더미데이터 리스트 -> 전역으로 관리해야함. 커뮤니티를 벗어나면 클리어되게 설정. 그게 아니라면 유지돼야함.
    var buttons by remember { mutableStateOf(listOf("좋아요 순", "최신순", "추천순")) }
    var selectedButtonIndex by remember { mutableStateOf(0) }

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
            CommunitySearchBar(onSearch = { searchState = false })
            // 디자인 다듬기
            // 버튼들을 배열을 사용해서 나타냄. 버튼에 넣는 값들을 배열에 있는 것으로 클래스로 구분해서 사용. ex) Main Screen에서 결과값 사진 or Navgraph
            Row(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp),
                horizontalArrangement = Arrangement.Start,
                //verticalAlignment = Alignment.CenterVertically
            ) {
                buttons.forEachIndexed { index, label ->
                    OutlinedButton(
                        modifier = Modifier.size(width = 85.dp, height = 35.dp),
                        onClick = {
                            selectedButtonIndex = index
                            when(label){ // 버튼 클릭시 기능 실행
                                "좋아요 순" -> println("좋아요순 버튼 클릭")
                                "최신순" -> println("최신순 버튼 클릭")
                                "추천순" -> println("추천순 버튼 클릭")
                            }
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = if (selectedButtonIndex == index) MaterialTheme.colors.primaryVariant else Color.White),
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
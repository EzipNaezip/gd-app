package com.example.gd.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gd.Effects.LoadingShimmerEffect
import com.example.gd.Effects.SearchBar
import com.example.gd.Effects.SearchResult
import com.example.gd.R

@Composable
fun MainScreen(navController: NavHostController) {
    var searchState by remember { mutableStateOf(true) }

    // 테스트용 이미지. 추후 변경 예정. listOf()로 만들고, Api 호출전에 다시 초기화 하고, 값을 하나씩 넣는 방법 사용
    val testImage: List<Int> =
        listOf(R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .border(0.dp, Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        //Spacer(modifier = Modifier.padding(8.dp))
        Card(
            modifier = Modifier.border(0.dp, Color.Transparent),
            elevation = 0.dp
        ) {
            SearchBar( // 검색창
                onSearch = {
                    searchState = false
                    // 검색어로 검색한 결과 나타내는 코드 예정 => Api 호출
                },
                modifier = Modifier.fillMaxWidth()
            )

            if (searchState) {
                Image( //로고
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier.padding(vertical = 200.dp),
                    alpha = 0.2f
                )
            }
        }

        // api를 통해서 아래에 넣을 값을 가져온 후 아래 코드 실행
        // api를 통해서 가져오는 동안 로딩창 실행

        // searchState가 false가 되면 로딩창 실행.
        // 로딩창 하는동안 api를 통해 값 가져옴
        // 값을 받으면 실행

        if (!searchState) {
            //LoadingShimmerEffect()

            if (!false) { // API사용해서 값 가져오면 실행.
                SearchResult(testImage)
            }
        }

    }
}
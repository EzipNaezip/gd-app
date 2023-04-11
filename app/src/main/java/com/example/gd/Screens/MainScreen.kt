package com.example.gd.Screens

import androidx.annotation.DrawableRes
import androidx.compose.animation.*
import androidx.compose.material.TextField
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.gd.Effects.LoadingShimmerEffect
import com.example.gd.Effects.SearchBar
import com.example.gd.Effects.SearchResult
import com.example.gd.R
import com.example.gd.ui.IconPack
import com.example.gd.ui.iconpack.Home
import com.example.gd.ui.theme.suite
import kotlinx.coroutines.*

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
            LoadingShimmerEffect()

            if (false) { // API사용해서 값 가져오면 실행.
                SearchResult(testImage)
            }
        }

    }
}

@Composable
fun ImageScreen(@DrawableRes imageId: Int) {
    var isExpanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = "Image",
            modifier = Modifier
                .fillMaxSize()
                .clickable { isExpanded = true }
                .clip(RoundedCornerShape(8.dp))
        )
        if (isExpanded) {
            Dialog(onDismissRequest = { isExpanded = false }) {
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = "Expanded Image",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .border(0.dp, Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.padding(8.dp))
            Card(
                modifier = Modifier.border(0.dp, Color.Transparent),
                elevation = 0.dp
            ) {
                if (searchState) {
                    Image( //로고
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "logo",
                        modifier = Modifier.padding(vertical = 200.dp),
                        alpha = 0.2f
                    )
                }

                SearchBar( // 검색창
                    onSearch = {
                        searchState = false
                        // 검색어로 검색한 결과 나타내는 코드 예정 => Api 호출
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // api를 통해서 아래에 넣을 값을 가져온 후 아래 코드 실행
            // api를 통해서 가져오는 동안 로딩창 실행

            // searchState가 false가 되면 로딩창 실행.
            // 로딩창 하는동안 api를 통해 값 가져옴
            // 값을 받으면 실행

            if (!searchState) {
                LoadingShimmerEffect()

                if (false){ // API사용해서 값 가져오면 실행.
                    SearchResult(testImage)
                }
            }

        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(onSearch: (String) -> Unit, modifier: Modifier) {
    var searchText by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = searchText,
        onValueChange = { searchText = it },
        placeholder = { Text("검색어를 입력하세요") },
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(16.dp)
            .background(Color.White),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                if (searchText.isNotEmpty()) {
                    onSearch(searchText)
                }
                focusManager.clearFocus()
                keyboardController?.hide()
            }),
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            disabledTextColor = Color.Transparent,
            backgroundColor = Color.LightGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        leadingIcon = {
            Box(
                modifier = Modifier.size(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.Gray
                )
            }
        },
        trailingIcon = {
            Icon(Icons.Default.Clear,
                contentDescription = "clear text",
                modifier = Modifier
                    .clickable {
                        searchText = ""
                    }
            )
        }
    )
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

@Composable
fun SearchResult(testImage: List<Int>){
    Column() {
        LazyVerticalGrid(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(testImage) { photo ->
                ImageScreen(photo)
            }
        }

        // 검색결과가 너무 길어질 경우 Surface로 구분할수도
        Text(
            text = "이런 내용 저런 내용 요런 내용 조런 내용 이런 내용 저런 내용 요런 내용 조런 내용 이런 내용 저런 내용 요런 내용 조런 내용 이런 내용 저런 내용 요런 내용 조런 내용 \n\n" +
                    "이런 내용 저런 내용 요런 내용 조런 내용 이런 내용 저런 내용 요런 내용 조런 내용 이런 내용 저런 내용 요런 내용 조런 내용 이런 내용 저런 내용 요런 내용 조런 내용 \n" +
                    "이런 내용 저런 내용 요런 내용 조런 내용 이런 내용 저런 내용 요런 내용 조런 내용 ",
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp),
            color = Color.Black,
            fontFamily = suite,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
        )

        Text(
            text = "#Tag1 #Tag2 #Tag3",
            modifier = Modifier.padding(vertical = 15.dp, horizontal = 15.dp),
            color = Color.Black,
            fontFamily = suite,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
        )

        // 북마크 버튼 사용. 저장되지 않으면 비어있는 북마크, 저장된 정보면 채워진 북마크 모양.
        // 누를때마다 state 변경. 빔 -> 참 /  참 -> 빔
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Button(
                elevation = ButtonDefaults.elevation(0.dp),
                shape = CircleShape,
                onClick = { /*TODO*/ }, // 스낵바 구현
            ) {
                Icon(
                    imageVector = IconPack.Home,
                    contentDescription = "Bookmark",
                    modifier = Modifier
                        .width(26.dp)
                        .height(26.dp)
                )
            }
        }
    }
}
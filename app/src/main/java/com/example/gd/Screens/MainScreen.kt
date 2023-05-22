package com.example.gd.Screens

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
import androidx.navigation.NavController
import com.example.gd.Button.BookmarkButton
import com.example.gd.Effects.*
import com.example.gd.R
import com.example.gd.ui.IconPack
import com.example.gd.ui.iconpack.Left
import com.example.gd.ui.theme.suite
import kotlinx.coroutines.delay
import java.util.Properties

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

        // modalFrame이 클릭되면 아래와 같이 실행
        var showDialog by rememberSaveable{ mutableStateOf(true) }
        PopUpModal(showDialog, onDismiss = {
            showDialog = false
        })

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
    // 매개변수로 해당 사진과 text를 받아야 함.
    // 클릭됐을때 띄워질 모달.
    // 사진과 글씨가 있어야함. 가로는 사진에 맞추고, 세로는 추가로 좀 더 해서 텍스트. 끝은 라운드로 깎여있어야함.
    // 자체 패딩을 없애야함.
    if (showDialog) {
        Dialog(
            onDismissRequest = { onDismiss() }
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(12.dp)

            ){
                Column {
                    Box(modifier = Modifier.fillMaxWidth()){
                        Image(
                            contentScale = ContentScale.Fit,
                            painter = painterResource(R.drawable.logo),
                            contentDescription = "Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                        )
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Back",
                            modifier = Modifier
                                .clickable {
                                    onDismiss()
                                }
                                .width(30.dp)
                                .height(30.dp)
                        )
                    }
                    PostContent()
                }
            }
        }
        /*
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = {
                Image(
                    contentScale = ContentScale.Fit,
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                ) },
            text = { Text(text = "모달 내용") },
            confirmButton = {
            },
            dismissButton = {
                Button(
                    onClick = { onDismiss() }
                ) {
                    Text(text = "닫기")
                }
            },
            modifier = Modifier.padding(0.dp)
        )

         */
    }

}
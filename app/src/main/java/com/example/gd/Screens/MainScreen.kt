//@file:OptIn(ExperimentalComposeUiApi::class)

package com.example.gd.Screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.TextField
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gd.R

@Composable
fun MainScreen(navController: NavHostController) {
    var showLogo by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            AnimatedVisibility(
                visible = showLogo,
                enter = slideInVertically(initialOffsetY = { -it }),
                exit = slideOutVertically(targetOffsetY = { -it })
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier.padding(vertical = 50.dp)
                )
            }

            AnimatedVisibility(
                visible = true,
                enter = slideInVertically(initialOffsetY = { -it }),
                exit = slideOutVertically(targetOffsetY = { -it })
            ) {
                SearchBar(
                    onSearch = {
                        showLogo = false
                        // 검색어로 검색한 결과 화면으로 이동하는 코드를 작성합니다.
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

//            Box(
//                modifier = Modifier
//                    .padding(horizontal = 16.dp)
//                    .offset(y = if (showLogo) 0.dp else (-10).dp)
//                    .animateContentSize(animationSpec = tween(400))
//            ) {
//                SearchBar(
//                    onSearch = {
//                        showLogo = false
//                        // 검색어로 검색한 결과 화면으로 이동하는 코드를 작성합니다.
//                    },
//                    modifier = Modifier.fillMaxWidth()
//                )
//            }
//            SearchBar(
//                onSearch = {
//                    showLogo = false
//                    // 검색어로 검색한 결과 화면으로 이동하는 코드를 작성합니다.
//                },
//                modifier = Modifier
//                    .padding(horizontal = 16.dp)
//                    .offset(y = if (showLogo) 0.dp else (-80).dp)
//                    .animateContentSize(animationSpec = tween(400))
//            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(onSearch: (String) -> Unit, modifier: Modifier) {
    val context = LocalContext.current
    var searchText by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = searchText,
        onValueChange = { searchText = it },
//        onKeyEvent = { keyEvent ->
//            if (keyEvent.key == Key.Enter) {
//                onSearch(searchText) // 검색어로 검색한 결과 화면으로 이동
//                keyboardController?.hide() // 키보드 숨기기
//                true
//            } else {
//                false
//            }
//        },
        placeholder = { Text("검색어를 입력하세요") },
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(16.dp)
            .background(Color.White)
            .onKeyEvent { keyEvent ->
                if (keyEvent.key == Key.Enter) {
                    onSearch(searchText) // 검색어로 검색한 결과 화면으로 이동
                    keyboardController?.hide() // 키보드 숨기기
                    true
                } else {
                    false
                }
            },
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            disabledTextColor = Color.Transparent,
            backgroundColor = Color.LightGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        singleLine = true,
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
        }
    )
}
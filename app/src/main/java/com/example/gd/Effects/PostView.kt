package com.example.gd.Effects

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gd.ui.IconPack
import com.example.gd.ui.theme.suite
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.navArgument
import com.example.gd.Button.BackButton
import com.example.gd.Button.BookmarkButton
import com.example.gd.Button.FavoriteButton
import com.example.gd.Screens.PRODUCT
import com.example.gd.ui.iconpack.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

@Composable
fun PostView(images: List<Int>, gridUI: Boolean = true) {
    Column(modifier = Modifier.fillMaxSize()) {
        // 사진 Pager로 표시 및 현재 페이지 표시
        PostUi(images = images)

        // 프로필 내용
        Row() {
            ProfileImage(50)

        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.BottomStart
                ) {

                }

                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Row {
                        BookmarkButton()
                        FavoriteButton()
                    }
                }
            }
        }


        PostContent()


        TagList(tags = PRODUCT.tags)

    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PostUi(images: List<Int>) {
    var nowImageIndex = rememberPagerState(0)

    val indexIcons: List<ImageVector> = listOf(
        IconPack.SquareSolid,
        IconPack.SquareSolid,
        IconPack.SquareSolid,
        IconPack.SquareSolid
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        HorizontalPager(
            count = images.size,
            state = nowImageIndex//rememberPagerState(0)
        ) { page ->
            ImageScreen(images[page])
        }
        Row() {
            indexIcons.forEachIndexed { index, icon ->
                Icon(
                    modifier = Modifier.size(15.dp),
                    imageVector = icon,
                    contentDescription = "Index Icon",
                    tint = if (index == nowImageIndex.currentPage) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.secondary

                )
            }
        }

    }
}



@Composable
fun PostContent(string: String =  "이런 내용 저런 내용 요런 내용 조런 내용 이런 내용 저런 내용 요런 내용 조런 내용 이런 내용 저런 내용 요런 내용 조런 내용 이런 내용 저런 내용 요런 내용 조런 내용 \n\n" +
        "이런 내용 저런 내용 요런 내용 조런 내용 이런 내용 저런 내용 요런 내용 조런 내용 이런 내용 저런 내용 요런 내용 조런 내용 이런 내용 저런 내용 요런 내용 조런 내용 \n" +
        "이런 내용 저런 내용 요런 내용 조런 내용 이런 내용 저런 내용 요런 내용 조런 내용 "){
    Text(
        text = string,
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 5.dp),
        color = Color.Black,
        fontFamily = suite,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp
    )
}

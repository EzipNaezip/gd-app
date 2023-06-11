package com.dongminpark.projectgd.Effects

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.dongminpark.projectgd.R
import com.dongminpark.projectgd.ui.theme.suite
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@Composable
fun PostView(images: ArrayList<String>, gridUI: Boolean = true) {
    Column(modifier = Modifier.fillMaxSize()) {
        // 사진 Pager로 표시 및 현재 페이지 표시
        PostUi(images = images)

        // 프로필 내용
        Row {
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
                ) { }

                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Row {
                        //BookmarkButton()
                        //FavoriteButton()
                    }
                }
            }
        }
        PostContent()

        //TagList(tags = PRODUCT.tags)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PostUi(images: ArrayList<String>) {
    var nowImageIndex = rememberPagerState(0)
    val indexIcons: List<Painter> = listOf(
        painterResource(id = R.drawable.circle),
        painterResource(id = R.drawable.circle),
        painterResource(id = R.drawable.circle),
        painterResource(id = R.drawable.circle),
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        HorizontalPager(
            count = images.size,
            state = nowImageIndex
        ) { page ->
            ImageScreen(images.get(page))
        }
        Row(modifier = Modifier.padding(3.dp)) {
            indexIcons.forEachIndexed { index, icon ->
                Icon(
                    modifier = Modifier.size(15.dp).padding(2.dp),
                    painter = icon,
                    contentDescription = "Index Icon",
                    tint = if (index == nowImageIndex.currentPage) MaterialTheme.colors.primaryVariant
                        else MaterialTheme.colors.secondary
                )
            }
        }

    }
}

@Composable
fun PostContent(string: String? =  "생성된 디자인이 없습니다."){
    Text(
        text = string?: "",
        color = Color.Black,
        fontFamily = suite,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp
    )
}

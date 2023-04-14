package com.example.gd.Effects

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gd.ui.IconPack
import com.example.gd.ui.theme.suite
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.window.Dialog
import com.example.gd.ui.iconpack.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SearchResult(images: List<Int>, gridUI: Boolean = true) {
    val indexIcons: List<ImageVector> = listOf(
        IconPack.SquareSolid,
        IconPack.SquareSolid,
        IconPack.SquareSolid,
        IconPack.SquareSolid
    )
    var nowImageIndex = rememberPagerState(0)

    Column(modifier = Modifier.fillMaxSize()) {
        if (gridUI) {
            //  초기 생성 버전. 사진 4개 그리드로 나타냄.
            LazyVerticalGrid(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp),
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(images) { photo ->
                    ImageScreen(photo)
                }
            }

        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                HorizontalPager(
                    count = images.size,
                    state = nowImageIndex//rememberPagerState(0)
                ) { page ->
                    ImageScreen(images[page])
                }
            }
        }

        // 가로로 한줄해서 내가 몇페이지를 보는지 나타내고, 하트 아이콘 추가
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                if (!gridUI) {
                    // 아이콘 4개. 현재 페이지만 포인트 색으로 색이 바뀜. 그 외는 회색으로. 선택안된 아이콘처럼
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
                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Row() {
                        if (gridUI) { // 검색 결과 => save 버튼
                            Icon(
                                imageVector = IconPack.Share, // save버튼으로 수정 필요
                                contentDescription = "Save",
                                modifier = Modifier
                                    .width(26.dp)
                                    .height(26.dp)
                                    .clickable {
                                        // 북마크 추가 기능
                                    }
                            )
                        }else{
                            if (true) { // 갤러리 -> 북마크 버튼 없음
                                Icon(
                                    imageVector = IconPack.BookmarkOutline,
                                    contentDescription = "Bookmark",
                                    modifier = Modifier
                                        .width(26.dp)
                                        .height(26.dp)
                                        .clickable {
                                            // 북마크 추가 기능
                                        }
                                )
                            }
                            Icon( // 모든 검색 결과에는 좋아요 기능이 필요함.
                                imageVector = IconPack.FavoriteOutline,
                                contentDescription = "Favorite",
                                modifier = Modifier
                                    .width(26.dp)
                                    .height(26.dp)
                                    .clickable {
                                        // favorite 추가 기
                                    }
                            )
                        }
                    }
                }
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
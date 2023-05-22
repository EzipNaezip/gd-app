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
import com.example.gd.Screens.PRODUCT
import com.example.gd.ui.iconpack.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@Composable
fun SearchResult(images: List<Int>) {
    Column(modifier = Modifier.fillMaxSize()) {
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

        //
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
                contentAlignment = Alignment.CenterEnd
            ) {
                Row() {

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

        TagList(tags = PRODUCT.tags)

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

@Composable
fun TagList(tags: List<String>) {
    Row(modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp)) {
        for (i in tags.indices) {
            Row {
                Text(
                    text = "#${tags[i]}",
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 2.dp),
                    color = Color.Black,
                    fontFamily = suite,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
            }
        }
    }
}
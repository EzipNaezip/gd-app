package com.example.gd.Effects

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gd.Screens.ImageScreen
import com.example.gd.ui.IconPack
import com.example.gd.ui.iconpack.Comunity
import com.example.gd.ui.iconpack.Home
import com.example.gd.ui.theme.suite

@Composable
fun SearchResult(testImage: List<Int>, isbook: Boolean = true) {
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
                if (isbook) { // 검색 결과
                    Icon(
                        imageVector = IconPack.Home,
                        contentDescription = "Bookmark",
                        modifier = Modifier
                            .width(26.dp)
                            .height(26.dp)
                    )
                }else{ // 커뮤니티 상세정보
                    Icon(
                        imageVector = IconPack.Comunity,
                        contentDescription = "Favorite",
                        modifier = Modifier
                            .width(26.dp)
                            .height(26.dp)
                    )
                }
            }
        }
    }
}
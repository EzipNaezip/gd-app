package com.example.gd.Effects

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gd.Screens.PRODUCT
import com.example.gd.Screens.Product
import com.example.gd.navigation.BottomScreen
import com.example.gd.ui.IconPack
import com.example.gd.ui.iconpack.BookmarkOutline

@Composable
fun productFrame(product: Product, navController: NavHostController, isMine: Boolean = true) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .border(0.dp, Color.Transparent)
            .clickable {
                PRODUCT = product
                navController.navigate(BottomScreen.Detail.screenRoute)
            }.padding(horizontal = 2.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box() {
            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(id = product.imageId[0]),
                contentDescription = "Image",
                modifier = Modifier
                    //.fillMaxSize()
                    .size(200.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            if (isMine) {
                Icon(
                    imageVector = IconPack.BookmarkOutline,
                    contentDescription = "Bookmark",
                    modifier = Modifier
                        .clickable {
                            // Favorite 추가 기능
                            // Bottom Sheet or SnackBar를 사용해서 추가됐다는 것 표시
                        }
                        .width(30.dp)
                        .height(30.dp)
                        .align(Alignment.BottomEnd)
                )
            }
        }
    }
}
package com.example.gd.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gd.Effects.SearchResult
import com.example.gd.navigation.BottomScreen.Community.title
import com.example.gd.ui.IconPack
import com.example.gd.ui.iconpack.Comunity
import com.example.gd.ui.iconpack.Home
import com.example.gd.ui.theme.suite
import com.example.splashscreen.navigation.Screen

@Composable
fun DetailScreen(navController: NavHostController) {
    Column() {
        TopAppBar {
            Icon(
                imageVector = IconPack.Comunity,
                contentDescription = "Back",
                modifier = Modifier
                    .clickable {
                        navController.popBackStack()
                    }
                    .width(30.dp)
                    .height(30.dp)
            )
            Row(
                modifier = Modifier.clickable { // 프로필 창으로 이동
                }
            ) {
                Image(
                    painter = painterResource(id = PRODUCT.profilePicture),
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .padding(horizontal = 5.dp)
                )
                Text(
                    text = "${PRODUCT.name}",
                    modifier = Modifier.padding(vertical = 13.dp),
                    color = Color.Black,
                    fontFamily = suite,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Follow")
                }
            }
        }

        SearchResult(testImage = PRODUCT.imageId, isbook = false)
    }
}
package com.example.gd.Screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.gd.Effects.SearchResult
import com.example.gd.navigation.BottomScreen
import com.example.gd.ui.IconPack
import com.example.gd.ui.iconpack.Left
import com.example.gd.ui.theme.suite

@Composable
fun DetailScreen(navController: NavController, route: String) {
    Column() {
        TopAppBar {
            Icon(
                imageVector = IconPack.Left,
                contentDescription = "Back",
                modifier = Modifier
                    .clickable {
                        navController.popBackStack()
                    }
                    .width(30.dp)
                    .height(30.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Row(
                modifier = Modifier.clickable {
                    // 프로필 창으로 이동
                    navController.navigate(route + "_user_screen")
                }
            ) {
                Image(
                    painter = painterResource(id = PRODUCT.profilePicture),
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color.LightGray, CircleShape)
                )
                Spacer(modifier = Modifier.width(5.dp))
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

        SearchResult(PRODUCT.imageId, false)
    }
}
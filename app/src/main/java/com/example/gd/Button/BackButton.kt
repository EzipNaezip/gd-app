package com.example.gd.Button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gd.ui.IconPack
import com.example.gd.ui.iconpack.Left

@Composable
fun BackButton(navController: NavController){
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
}
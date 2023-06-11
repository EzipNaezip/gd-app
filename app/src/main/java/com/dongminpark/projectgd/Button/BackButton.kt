package com.dongminpark.projectgd.Button

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun BackButton(navController: NavController){
    IconButton(
        onClick = { navController.popBackStack() }
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back Button",
            tint = MaterialTheme.colors.onPrimary
        )
    }
}
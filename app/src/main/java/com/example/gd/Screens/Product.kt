package com.example.gd.Screens

import androidx.annotation.DrawableRes
import com.example.gd.R

class Product(
    val name: String, val info: String,
    @DrawableRes val profilePicture: Int,
    @DrawableRes val imageId: List<Int>
)

var PRODUCT = Product("", "", R.drawable.logo, listOf())
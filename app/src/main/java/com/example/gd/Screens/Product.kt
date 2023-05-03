package com.example.gd.Screens

import androidx.annotation.DrawableRes
import com.example.gd.R

class Product(
    val name: String, val info: String,
    val date: String, val tags: List<String>,
    @DrawableRes val profilePicture: Int,
    @DrawableRes val imageId: List<Int>,
    var is_me: Boolean
)

var PRODUCT = Product("", "", "", listOf(), R.drawable.logo, listOf(), true)
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

var PRODUCT = Product(
    "User Name",
    "안녕하세요",
    "2023-04-14 21:45:51",
    listOf("모던", "엘레강스", "고딕"),
    R.drawable.logo,
    listOf(R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo),
    true
)
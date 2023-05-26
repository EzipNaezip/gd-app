package com.example.gd.Screens

import androidx.annotation.DrawableRes
import com.example.gd.R

class MainExampleItem(
    @DrawableRes val image: Int,
    val context: String
)

var EXITEM = MainExampleItem(
    R.drawable.logo,
    "이런 내용 저런 내용 요런 내용 조런 내용 이런 내용 저런 내용 요런 내용 조런 내용 이런 내용 저런 내용 요런 내용 조런 내용 이런 내용 저런 내용 요런 내용 조런 내용 \n\n" +
        "이런 내용 저런 내용 요런 내용 조런 내용 이런 내용 저런 내용 요런 내용 조런 내용 이런 내용 저런 내용 요런 내용 조런 내용 이런 내용 저런 내용 요런 내용 조런 내용 \n" +
        "이런 내용 저런 내용 요런 내용 조런 내용 이런 내용 저런 내용 요런 내용 조런 내용 "
)
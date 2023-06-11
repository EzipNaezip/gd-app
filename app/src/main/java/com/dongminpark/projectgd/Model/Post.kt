package com.dongminpark.projectgd.Model

data class Post(
    val postNum: Int,
    val thumbnailImgUrl: String?,
    val bookmark: Boolean,
    val me: Boolean
)
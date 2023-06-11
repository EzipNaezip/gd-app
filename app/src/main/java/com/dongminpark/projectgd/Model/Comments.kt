package com.dongminpark.projectgd.Model

data class Comments(
    val serialNum: Int,
    val userId: Int,
    val name: String,
    val profileImgUrl: String,
    val content: String,
    val isMe: Boolean
)

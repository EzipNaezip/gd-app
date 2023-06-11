package com.dongminpark.projectgd.Model

data class PostPostnum(
    val postNum: Int = 0,
    val timestamp: String = "",
    val userId: Int = 0,
    val name: String = "",
    val profileImgUrl: String = "",
    val likesCount: Int = 0,
    val path: ArrayList<String> = arrayListOf(),
    var bookmark: Boolean = false,
    var like: Boolean = false,
    var follow: Boolean = false,
    val isMe: Boolean = false,
    val tagIds: ArrayList<String> = arrayListOf(),
    val description: String = ""
)

package com.dongminpark.projectgd.Model

data class User(
    val userId: Int = 0,
    val name: String = "",
    val email: String = "",
    val description: String = "",
    val profileImgUrl: String = "",
    val postCount: Int = 0,
    val followerCount: Int = 0,
    val followCount: Int = 0,
    val isMe: Boolean = false
)
package com.example.gd.Effects

fun NumberContraction(num: Int): String{
    val M = 1000000
    val K = 1000

    if (num >= M) return "${num / M} M"
    else if (num >= K) return "${num / K} K"

    return num.toString()
}
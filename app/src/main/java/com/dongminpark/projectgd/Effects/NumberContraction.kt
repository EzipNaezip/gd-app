package com.dongminpark.projectgd.Effects

fun NumberContraction(num: Int): String{
    val M = 1000000
    val K = 1000

    if (num >= M) return "${num / M}.${num*10/M % 10} M"
    else if (num >= K) return "${num / K}.${num*10/M % 10} K"

    return num.toString()
}
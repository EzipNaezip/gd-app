package com.dongminpark.projectgd.Utils

// 문자열이 제이슨 형태인지, 제이슨 배열 형태인지
fun String?.isJsonObject():Boolean = this?.startsWith("{") == true && this.endsWith("}")

fun String?.isJsonArray(): Boolean = this?.startsWith("[") == true && this.endsWith("]")
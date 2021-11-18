package com.mohamed.halim.essa.cryptoexchange.utils

import com.google.gson.Gson

fun stringFromList(list: List<String>): String {
    return Gson().toJson(list)
}

fun listFromString(string: String): List<String> {
    return Gson().fromJson(string, List::class.java) as List<String>
}
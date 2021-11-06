package com.mohamed.halim.essa.cryptoexchange.utils

import java.text.SimpleDateFormat
import java.util.*

object IsoTimeUtils {
    private val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    fun fromIso(time: String): Long {
        return formatter.parse(time).time
    }

    fun toIso(time: Long): String {
        return formatter.format(Date(time));
    }
}
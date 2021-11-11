package com.mohamed.halim.essa.cryptoexchange.data.domain.rate

data class RateHistory(
    val rateClose: Double,
    val rateHigh: Double,
    val rateLow: Double,
    val rateOpen: Double,
    val timeClose: Long,
    val timeOpen: Long,
    val timePeriodEnd: Long,
    val timePeriodStart: Long
)

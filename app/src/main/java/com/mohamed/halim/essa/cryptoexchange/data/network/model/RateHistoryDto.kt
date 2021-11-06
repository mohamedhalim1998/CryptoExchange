package com.mohamed.halim.essa.cryptoexchange.data.network.model


import com.google.gson.annotations.SerializedName

data class RateHistoryDto(
    @SerializedName("rate_close")
    val rateClose: Double,
    @SerializedName("rate_high")
    val rateHigh: Double,
    @SerializedName("rate_low")
    val rateLow: Double,
    @SerializedName("rate_open")
    val rateOpen: Double,
    @SerializedName("time_close")
    val timeClose: String,
    @SerializedName("time_open")
    val timeOpen: String,
    @SerializedName("time_period_end")
    val timePeriodEnd: String,
    @SerializedName("time_period_start")
    val timePeriodStart: String
)
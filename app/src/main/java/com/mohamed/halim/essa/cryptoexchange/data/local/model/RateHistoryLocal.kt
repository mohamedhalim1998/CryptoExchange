package com.mohamed.halim.essa.cryptoexchange.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mohamed.halim.essa.cryptoexchange.utils.HistoryPeriod


@Entity(tableName = "Rate History")
data class RateHistoryLocal(
    val realCurrencyId: String,
    val cryptoCurrency: String,
    val historyPeriod: HistoryPeriod,
    val rateClose: Double,
    val rateHigh: Double,
    val rateLow: Double,
    val rateOpen: Double,
    val timeClose: Long,
    val timeOpen: Long,
    val timePeriodEnd: Long,
    val timePeriodStart: Long,
    @PrimaryKey(autoGenerate = true)
    var id: Int? = -1,
)

package com.mohamed.halim.essa.cryptoexchange.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto")
data class CryptoCurrencyLocal(
    @PrimaryKey
    val cryptoId: String,
    val realCurrencyId: String,
    val name: String,
    val icon: String,
    val rate: Double,
    val time: Long,
)

package com.mohamed.halim.essa.cryptoexchange.data.domain


data class CryptoCurrency(
    val cryptoId: String,
    val name: String,
    val icon: String,
    val rate: Double,
    val time: Long,
)

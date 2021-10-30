package com.mohamed.halim.essa.cryptoexchange.data.domain


data class CryptoCurrency(
    val time: String,
    val cryptoId: String,
    val rate: Double,
    val icon: String,
    val name: String,
)

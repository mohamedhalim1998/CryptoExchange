package com.mohamed.halim.essa.cryptoexchange.data.network.model

import com.google.gson.annotations.SerializedName

/*
 "time": "2021-10-29T19:15:56.0000000Z",
      "asset_id_quote": "ADA",
      "rate": 2.008061955142870288176392317
 */
data class CryptoCurrencyDto(
    @SerializedName("time")
    val time: String,
    @SerializedName("asset_id_quote")
    val cryptoId: String,
    @SerializedName("rate")
    val rate: Double
)

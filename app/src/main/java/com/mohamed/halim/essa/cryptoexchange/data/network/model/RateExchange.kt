package com.mohamed.halim.essa.cryptoexchange.data.network.model

import com.google.gson.annotations.SerializedName

data class RateExchange(
    @SerializedName("asset_id_base")
    val assetId: String,
    @SerializedName("rates")
    val rates: List<CryptoCurrencyDto>
)

package com.mohamed.halim.essa.cryptoexchange.data.network.model

import com.google.gson.annotations.SerializedName


data class CurrencyInfo(
    @SerializedName("asset_id")
    val currencyId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("type_is_crypto")
    val isCrypto: Int,
)

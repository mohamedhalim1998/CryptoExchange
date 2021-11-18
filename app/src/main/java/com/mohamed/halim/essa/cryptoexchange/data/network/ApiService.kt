package com.mohamed.halim.essa.cryptoexchange.data.network


import com.mohamed.halim.essa.cryptoexchange.data.network.model.CurrencyInfo
import com.mohamed.halim.essa.cryptoexchange.data.network.model.RateExchangeResponse
import com.mohamed.halim.essa.cryptoexchange.data.network.model.RateHistoryDto
import com.mohamed.halim.essa.cryptoexchange.utils.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("v1/exchangerate/{real_currency}?&invert=true&apikey=$API_KEY")
    suspend fun getRates(
        @Path("real_currency") realCurrency: String,
        @Query("filter_asset_id") cryptoCurrencies: String
    ): RateExchangeResponse

    @GET("v1/assets?&apikey=$API_KEY")
    suspend fun getCurrencyInfo(
        @Query("filter_asset_id") cryptoCurrencies: String

    ): List<CurrencyInfo>

    @GET("v1/exchangerate/{asset_id}/USD/history?&apikey=$API_KEY")
    suspend fun getCryptoHistory(
        @Path("asset_id") assetId: String,
        @Query("period_id") period: String,
        @Query("time_start") startTime: String,
        @Query("time_end") endTime: String,
    ): List<RateHistoryDto>
}
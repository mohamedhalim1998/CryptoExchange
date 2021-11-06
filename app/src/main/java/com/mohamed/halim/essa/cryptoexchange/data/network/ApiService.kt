package com.mohamed.halim.essa.cryptoexchange.data.network


import com.mohamed.halim.essa.cryptoexchange.data.network.model.CurrencyInfo
import com.mohamed.halim.essa.cryptoexchange.data.network.model.RateExchangeResponse
import com.mohamed.halim.essa.cryptoexchange.data.network.model.RateHistoryDto
import com.mohamed.halim.essa.cryptoexchange.utils.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("v1/exchangerate/USD?filter_asset_id=[,SHIB,DOGE,DOT,XRP,SOL,ADA,USDT,BNB,ETH,BTC,]&invert=true&apikey=$API_KEY")
    suspend fun getRates(): RateExchangeResponse

    @GET("v1/assets?filter_asset_id=[,SHIB,DOGE,DOT,XRP,ADA,USDT,BNB,ETH,BTC,SOL,]&apikey=$API_KEY")
    suspend fun getCurrencyInfo(): List<CurrencyInfo>

    @GET("v1/exchangerate/{asset_id}/USD/history?&apikey=$API_KEY")
    suspend fun getCryptoHistory(
        @Path("asset_id") assetId: String,
        @Query("period_id") period: String,
        @Query("time_start") startTime: String,
        @Query("time_end") endTime: String,
    ): List<RateHistoryDto>
}
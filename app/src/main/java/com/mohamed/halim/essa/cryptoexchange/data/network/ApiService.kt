package com.mohamed.halim.essa.cryptoexchange.data.network


import com.mohamed.halim.essa.cryptoexchange.data.network.model.CurrencyInfo
import com.mohamed.halim.essa.cryptoexchange.data.network.model.RateExchangeResponse
import com.mohamed.halim.essa.cryptoexchange.utils.API_KEY
import retrofit2.http.GET

interface ApiService {
    @GET("/v1/exchangerate/USD?filter_asset_id=[,SHIB,DOGE,DOT,XRP,SOL,ADA,USDT,BNB,ETH,BTC,]&invert=true&$API_KEY")
    suspend fun getRates(): RateExchangeResponse
    @GET("/v1/assets?filter_asset_id=[,SHIB,DOGE,DOT,XRP,ADA,USDT,BNB,ETH,BTC,SOL,]&apikey=40EBD296-8592-4B3E-9DAF-8A6E398C1D92")
    suspend fun getCurrencyInfo(): List<CurrencyInfo>
}
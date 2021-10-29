package com.mohamed.halim.essa.cryptoexchange.data.network


import com.mohamed.halim.essa.cryptoexchange.data.network.model.RateExchangeResponse
import com.mohamed.halim.essa.cryptoexchange.utils.API_KEY
import retrofit2.http.GET

interface ApiService {
    @GET("/v1/exchangerate/USD?filter_asset_id=[,SHIB,DOGE,DOT,XRP,SOL,ADA,ADA,USDT,BNB,ETH,BTC,]&invert=true&$API_KEY")
    suspend fun getRates(): RateExchangeResponse
}
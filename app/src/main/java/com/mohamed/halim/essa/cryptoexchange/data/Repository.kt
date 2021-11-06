package com.mohamed.halim.essa.cryptoexchange.data

import android.util.Log
import com.mohamed.halim.essa.cryptoexchange.data.domain.cryptocurrency.CryptoCurrency
import com.mohamed.halim.essa.cryptoexchange.data.network.ApiService
import com.mohamed.halim.essa.cryptoexchange.data.network.model.CryptoCurrencyDto
import com.mohamed.halim.essa.cryptoexchange.data.network.model.CurrencyInfo
import com.mohamed.halim.essa.cryptoexchange.data.network.model.CryptoDtoToDomainMapper
import com.mohamed.halim.essa.cryptoexchange.data.network.model.RateInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

private const val TAG = "Repository"

class Repository(private val networkSource: ApiService) {
    fun getCurrentRate(): Flow<List<CryptoCurrency>> {
        return flow {
            try {
                val rates = networkSource.getRates();
                Log.e(TAG, "getCurrentRate: $rates")
                val currencyInfoList = networkSource.getCurrencyInfo()
                Log.e(TAG, "getCurrentRate: $currencyInfoList")
                val cryptoCurrencyDtoList = convertToDto(rates.rates, currencyInfoList)
                emit(CryptoDtoToDomainMapper.toDomainList(cryptoCurrencyDtoList))
            } catch (e: Exception) {
                Log.e(TAG, "getCurrentRate: ", e)
            }
        }
    }

    private fun convertToDto(
        rates: List<RateInfo>,
        currencyInfoList: List<CurrencyInfo>
    ): MutableList<CryptoCurrencyDto> {
        val currencyInfoMap = convertToMap(currencyInfoList)
        val cryptoCurrencyDtoList = mutableListOf<CryptoCurrencyDto>()
        rates.forEach {
            cryptoCurrencyDtoList.add(CryptoCurrencyDto(currencyInfoMap[it.cryptoId]!!, it))
        }
        return cryptoCurrencyDtoList
    }

    private fun convertToMap(currencyInfoList: List<CurrencyInfo>): HashMap<String, CurrencyInfo> {
        val map = HashMap<String, CurrencyInfo>()
        currencyInfoList.forEach {
            map[it.currencyId] = it;
        }
        return map
    }

}
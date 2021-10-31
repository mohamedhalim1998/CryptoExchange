package com.mohamed.halim.essa.cryptoexchange.data

import com.mohamed.halim.essa.cryptoexchange.data.domain.CryptoCurrency
import com.mohamed.halim.essa.cryptoexchange.data.network.ApiService
import com.mohamed.halim.essa.cryptoexchange.data.network.model.CryptoCurrencyDto
import com.mohamed.halim.essa.cryptoexchange.data.network.model.CurrencyInfo
import com.mohamed.halim.essa.cryptoexchange.data.network.model.DtoToDomainMapper
import com.mohamed.halim.essa.cryptoexchange.data.network.model.RateInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository(private val networkSource: ApiService) {
    fun getCurrentRate(): Flow<List<CryptoCurrency>> {
        return flow {
            val rates = networkSource.getRates();
            val currencyInfoList = networkSource.getCurrencyInfo()
            val cryptoCurrencyDtoList = convertToDto(rates.rates, currencyInfoList)
            emit(DtoToDomainMapper.toDomainList(cryptoCurrencyDtoList))
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
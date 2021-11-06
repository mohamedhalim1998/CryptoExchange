package com.mohamed.halim.essa.cryptoexchange.data

import android.util.Log
import com.mohamed.halim.essa.cryptoexchange.data.domain.cryptocurrency.CryptoCurrency
import com.mohamed.halim.essa.cryptoexchange.data.domain.rate.RateHistoryDomain
import com.mohamed.halim.essa.cryptoexchange.data.network.ApiService
import com.mohamed.halim.essa.cryptoexchange.data.network.model.*
import com.mohamed.halim.essa.cryptoexchange.utils.HistoryPeriods
import com.mohamed.halim.essa.cryptoexchange.utils.IsoTimeUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.HashMap

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

    fun getCryptoHistoryHour(assetId: String): Flow<List<RateHistoryDomain>> {
        val calendar = Calendar.getInstance()
        val end = calendar.timeInMillis - calendar.timeZone.rawOffset
        val start = end - TimeUnit.HOURS.toMillis(1)
        return getCryptoHistory(assetId, HistoryPeriods.ONE_MINUTE, start, end)
    }

    fun getCryptoHistory12Hour(assetId: String): Flow<List<RateHistoryDomain>> {
        val calendar = Calendar.getInstance()
        val end = calendar.timeInMillis - calendar.timeZone.rawOffset
        val start = end - TimeUnit.HOURS.toMillis(12)
        return getCryptoHistory(assetId, HistoryPeriods.FIVE_MINUTE, start, end)
    }

    fun getCryptoHistoryDay(assetId: String): Flow<List<RateHistoryDomain>> {
        val calendar = Calendar.getInstance()
        val end = calendar.timeInMillis - calendar.timeZone.rawOffset
        val start = end - TimeUnit.DAYS.toMillis(1)
        return getCryptoHistory(assetId, HistoryPeriods.TEN_MINUTE, start, end)
    }

    private fun getCryptoHistory(
        assetId: String,
        period: String,
        startTime: Long,
        endTime: Long,
    ): Flow<List<RateHistoryDomain>> {
        return flow {
            try {
                emit(
                    RateHistoryDtoToDomain.toDomainList(
                        networkSource.getCryptoHistory(
                            assetId,
                            period,
                            IsoTimeUtils.toIso(startTime),
                            IsoTimeUtils.toIso(endTime)
                        )
                    )
                )
            } catch (e: Exception) {
                Log.e(TAG, "getCryptoHistory: $e")
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
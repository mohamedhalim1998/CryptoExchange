package com.mohamed.halim.essa.cryptoexchange.data

import android.util.Log
import com.mohamed.halim.essa.cryptoexchange.data.domain.cryptocurrency.CryptoCurrency
import com.mohamed.halim.essa.cryptoexchange.data.domain.rate.RateHistory
import com.mohamed.halim.essa.cryptoexchange.data.local.CryptoDao
import com.mohamed.halim.essa.cryptoexchange.data.local.model.CryptoLocalToDomain
import com.mohamed.halim.essa.cryptoexchange.data.local.model.RateHistoryLocalToDomain
import com.mohamed.halim.essa.cryptoexchange.data.network.ApiService
import com.mohamed.halim.essa.cryptoexchange.data.network.model.*
import com.mohamed.halim.essa.cryptoexchange.utils.HistoryPeriod
import com.mohamed.halim.essa.cryptoexchange.utils.IsoTimeUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.HashMap

private const val TAG = "Repository"

class Repository(private val networkSource: ApiService, private val localSource: CryptoDao) {
    fun getCurrentRate(
        realCurrencyId: String,
        cryptoCurrenciesId: String
    ): Flow<List<CryptoCurrency>> {
        Log.d(TAG, "getCurrentRate: $realCurrencyId \n $cryptoCurrenciesId")
        return flow {
            try {
                val rates = networkSource.getRates(realCurrencyId, cryptoCurrenciesId)
                Log.d(TAG, "getCurrentRate: $rates")
                val currencyInfoList = networkSource.getCurrencyInfo(cryptoCurrenciesId)
                val cryptoCurrencyDtoList = convertToDto(rates.rates, currencyInfoList)
                val domainList = CryptoDtoToDomainMapper.toDomainList(cryptoCurrencyDtoList);
                localSource.deleteCrypto(realCurrencyId)
                localSource.insertCrypto(
                    CryptoLocalToDomain.fromDomainList(
                        domainList,
                        realCurrencyId
                    )
                )
            } catch (e: Exception) {
                Log.e(TAG, "getCurrentRate: ", e)
            } finally {
                emit(
                    CryptoLocalToDomain.toDomainList(
                        localSource.getCurrentRates(realCurrencyId)
                    )
                )
            }
        }
    }

    fun getCurrentRate(cryptoId: String): Flow<CryptoCurrency> {
        return flow {
            emit(
                CryptoLocalToDomain.toDomain(
                    localSource.getCurrentRates("USD", cryptoId)
                )
            )
        }
    }

    fun getCryptoHistoryHour(assetId: String): Flow<List<RateHistory>> {
        val calendar = Calendar.getInstance()
        val end = calendar.timeInMillis - calendar.timeZone.rawOffset
        val start = end - TimeUnit.HOURS.toMillis(1)
        return getCryptoHistory(assetId, HistoryPeriod.ONE_MINUTE, start, end)
    }

    fun getCryptoHistory12Hour(assetId: String): Flow<List<RateHistory>> {
        val calendar = Calendar.getInstance()
        val end = calendar.timeInMillis - calendar.timeZone.rawOffset
        val start = end - TimeUnit.HOURS.toMillis(12)
        return getCryptoHistory(assetId, HistoryPeriod.FIVE_MINUTE, start, end)
    }

    fun getCryptoHistoryDay(assetId: String): Flow<List<RateHistory>> {
        val calendar = Calendar.getInstance()
        val end = calendar.timeInMillis - calendar.timeZone.rawOffset
        val start = end - TimeUnit.DAYS.toMillis(1)
        return getCryptoHistory(assetId, HistoryPeriod.TEN_MINUTE, start, end)
    }

    private fun getCryptoHistory(
        assetId: String,
        period: HistoryPeriod,
        startTime: Long,
        endTime: Long,
    ): Flow<List<RateHistory>> {
        return flow {
            try {
                val domainList = RateHistoryDtoToDomain.toDomainList(
                    networkSource.getCryptoHistory(
                        assetId,
                        period.value,
                        IsoTimeUtils.toIso(startTime),
                        IsoTimeUtils.toIso(endTime)
                    )
                )
                localSource.deleteHistoryRate("USD", assetId, period)

                localSource.insertRateHistory(
                    RateHistoryLocalToDomain.fromDomainList(
                        domainList,
                        "USD", assetId, period
                    )
                )

            } catch (e: Exception) {
                Log.e(TAG, "getCryptoHistory: $e")
            } finally {
                emit(
                    RateHistoryLocalToDomain.toDomainList(
                        localSource.getCryptoHistory(
                            "USD",
                            assetId,
                            period
                        )
                    )
                )
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
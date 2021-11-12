package com.mohamed.halim.essa.cryptoexchange.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mohamed.halim.essa.cryptoexchange.data.local.model.CryptoCurrencyLocal
import com.mohamed.halim.essa.cryptoexchange.data.local.model.RateHistoryLocal
import com.mohamed.halim.essa.cryptoexchange.utils.HistoryPeriod
import kotlinx.coroutines.flow.Flow


@Dao
interface CryptoDao {
    @Query("SELECT * FROM crypto WHERE realCurrencyId = :realCurrencyId")
    fun getCurrentRates(realCurrencyId: String): Flow<List<CryptoCurrencyLocal>>

    @Query("SELECT * FROM `Rate History` WHERE realCurrencyId = :realCurrencyId AND historyPeriod = :historyPeriod")
    fun getCryptoHistoryHour(
        realCurrencyId: String,
        historyPeriod: HistoryPeriod
    ): Flow<List<RateHistoryLocal>>

    @Insert
    suspend fun insertCrypto(cryptoCurrencyLocal: CryptoCurrencyLocal)

    @Insert
    suspend fun insertCrypto(cryptoCurrencyLocalList: List<CryptoCurrencyLocal>)

    @Insert
    suspend fun insertRateHistory(rateHistoryLocal: RateHistoryLocal)

    @Insert
    suspend fun insertRateHistory(rateHistoryLocalList: List<RateHistoryLocal>)


    @Query("DELETE FROM crypto")
    suspend fun deleteCrypto()

    @Query("DELETE FROM `Rate History` WHERE historyPeriod = :historyPeriod")
    suspend fun deleteHistoryRate(historyPeriod: HistoryPeriod)

}
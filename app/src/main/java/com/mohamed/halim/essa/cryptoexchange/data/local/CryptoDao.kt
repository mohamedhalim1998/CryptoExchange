package com.mohamed.halim.essa.cryptoexchange.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mohamed.halim.essa.cryptoexchange.data.local.model.CryptoCurrencyLocal
import com.mohamed.halim.essa.cryptoexchange.data.local.model.RateHistoryLocal
import com.mohamed.halim.essa.cryptoexchange.utils.HistoryPeriod
import kotlinx.coroutines.flow.Flow


@Dao
interface CryptoDao {
    @Query("SELECT * FROM crypto WHERE realCurrencyId = :realCurrencyId")
    suspend fun getCurrentRates(realCurrencyId: String): List<CryptoCurrencyLocal>

    @Query("SELECT * FROM crypto WHERE realCurrencyId = :realCurrencyId AND cryptoId = :cryptoCurrencyId")
    suspend fun getCurrentRates(
        realCurrencyId: String,
        cryptoCurrencyId: String
    ): CryptoCurrencyLocal

    @Query("SELECT * FROM `Rate History` WHERE realCurrencyId = :realCurrencyId AND historyPeriod = :historyPeriod AND cryptoCurrency = :cryptoCurrency")
    suspend fun getCryptoHistory(
        realCurrencyId: String,
        cryptoCurrency: String,
        historyPeriod: HistoryPeriod
    ): List<RateHistoryLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrypto(cryptoCurrencyLocal: CryptoCurrencyLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrypto(cryptoCurrencyLocalList: List<CryptoCurrencyLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRateHistory(rateHistoryLocal: RateHistoryLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRateHistory(rateHistoryLocalList: List<RateHistoryLocal>)


    @Query("DELETE FROM crypto WHERE realCurrencyId = :realCurrencyId")
    suspend fun deleteCrypto(realCurrencyId: String)

    @Query("DELETE FROM `Rate History` WHERE historyPeriod = :historyPeriod AND realCurrencyId = :realCurrencyId AND cryptoCurrency = :cryptoCurrency")
    suspend fun deleteHistoryRate(
        realCurrencyId: String,
        cryptoCurrency: String,
        historyPeriod: HistoryPeriod
    )

}
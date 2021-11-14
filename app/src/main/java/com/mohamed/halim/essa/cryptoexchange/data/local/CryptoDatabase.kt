package com.mohamed.halim.essa.cryptoexchange.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mohamed.halim.essa.cryptoexchange.data.local.model.CryptoCurrencyLocal
import com.mohamed.halim.essa.cryptoexchange.data.local.model.RateHistoryLocal
import com.mohamed.halim.essa.cryptoexchange.utils.RoomConverters

@Database(entities = [CryptoCurrencyLocal::class, RateHistoryLocal::class], version = 2)
@TypeConverters(RoomConverters::class)
abstract class CryptoDatabase : RoomDatabase() {
    abstract fun cryptoDao(): CryptoDao
}
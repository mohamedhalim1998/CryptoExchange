package com.mohamed.halim.essa.cryptoexchange.utils

import androidx.room.TypeConverter

class RoomConverters {
    @TypeConverter
    fun convertFromHistoryPeriod(historyPeriod: HistoryPeriod): String {
        return historyPeriod.name
    }

    @TypeConverter
    fun convertToHistoryPeriod(historyPeriod: String): HistoryPeriod {
        return HistoryPeriod.valueOf(historyPeriod)
    }

}
package com.mohamed.halim.essa.cryptoexchange.data.local.model

import com.mohamed.halim.essa.cryptoexchange.data.domain.rate.RateHistory
import com.mohamed.halim.essa.cryptoexchange.data.domain.rate.RateHistoryDomainMapper
import com.mohamed.halim.essa.cryptoexchange.utils.HistoryPeriod

object RateHistoryLocalToDomain : RateHistoryDomainMapper<RateHistoryLocal> {
    override fun toDomain(rateHistory: RateHistoryLocal): RateHistory {
        return RateHistory(
            rateHistory.rateClose,
            rateHistory.rateHigh,
            rateHistory.rateLow,
            rateHistory.rateOpen,
            rateHistory.timeClose,
            rateHistory.timeOpen,
            rateHistory.timePeriodEnd,
            rateHistory.timePeriodStart,
        )
    }

    override fun toDomainList(rateHistoryList: List<RateHistoryLocal>): List<RateHistory> {
        return rateHistoryList.map {
            toDomain(it)
        }
    }

    fun fromDomain(
        rateHistory: RateHistory,
        realCurrencyId: String,
        historyPeriod: HistoryPeriod
    ): RateHistoryLocal {
        return RateHistoryLocal(
            realCurrencyId,
            historyPeriod,
            rateHistory.rateClose,
            rateHistory.rateHigh,
            rateHistory.rateLow,
            rateHistory.rateOpen,
            rateHistory.timeClose,
            rateHistory.timeOpen,
            rateHistory.timePeriodEnd,
            rateHistory.timePeriodStart,
        )
    }

    fun fromDomainList(
        rateHistoryList: List<RateHistory>,
        realCurrencyId: String,
        historyPeriod: HistoryPeriod
    ): List<RateHistoryLocal> {
        return rateHistoryList.map {
            fromDomain(it, realCurrencyId, historyPeriod)
        }
    }


}
package com.mohamed.halim.essa.cryptoexchange.data.network.model

import com.mohamed.halim.essa.cryptoexchange.data.domain.rate.RateHistoryDomain
import com.mohamed.halim.essa.cryptoexchange.data.domain.rate.RateHistoryDomainMapper
import com.mohamed.halim.essa.cryptoexchange.utils.IsoTimeUtils

object RateHistoryDtoToDomain : RateHistoryDomainMapper<RateHistoryDto> {

    override fun toDomain(rateHistory: RateHistoryDto): RateHistoryDomain {
        return RateHistoryDomain(
            rateHistory.rateClose,
            rateHistory.rateHigh,
            rateHistory.rateLow,
            rateHistory.rateOpen,
            IsoTimeUtils.fromIso(rateHistory.timeClose.dropLast(9)),
            IsoTimeUtils.fromIso(rateHistory.timeOpen.dropLast(9)),
            IsoTimeUtils.fromIso(rateHistory.timePeriodEnd.dropLast(9)),
            IsoTimeUtils.fromIso(rateHistory.timePeriodStart.dropLast(9)),
        )
    }

    override fun toDomainList(rateHistoryList: List<RateHistoryDto>): List<RateHistoryDomain> {
        return rateHistoryList.map { toDomain(it) }
    }
}
package com.mohamed.halim.essa.cryptoexchange.data.domain.rate

interface RateHistoryDomainMapper<T> {

    fun toDomain(rateHistory: T) : RateHistoryDomain
    fun toDomainList(rateHistoryList: List<T>) : List<RateHistoryDomain>

}
package com.mohamed.halim.essa.cryptoexchange.data.domain

interface DomainMapper<T> {
    fun toDomain(model: T): CryptoCurrency
    fun toDomainList(model: List<T>): List<CryptoCurrency>
}
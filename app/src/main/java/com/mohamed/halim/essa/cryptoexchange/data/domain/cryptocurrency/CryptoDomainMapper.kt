package com.mohamed.halim.essa.cryptoexchange.data.domain.cryptocurrency

interface CryptoDomainMapper<T> {
    fun toDomain(model: T): CryptoCurrency
    fun toDomainList(model: List<T>): List<CryptoCurrency>
}
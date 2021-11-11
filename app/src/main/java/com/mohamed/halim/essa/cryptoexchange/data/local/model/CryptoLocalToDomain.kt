package com.mohamed.halim.essa.cryptoexchange.data.local.model

import com.mohamed.halim.essa.cryptoexchange.data.domain.cryptocurrency.CryptoCurrency
import com.mohamed.halim.essa.cryptoexchange.data.domain.cryptocurrency.CryptoDomainMapper

object CryptoLocalToDomain : CryptoDomainMapper<CryptoCurrencyLocal> {
    override fun toDomain(model: CryptoCurrencyLocal): CryptoCurrency {
        return CryptoCurrency(
            model.cryptoId,
            model.name,
            model.icon,
            model.rate,
            model.time
        )
    }

    override fun toDomainList(model: List<CryptoCurrencyLocal>): List<CryptoCurrency> {
        return model.map {
            toDomain(it)
        }
    }

    fun fromDomain(model: CryptoCurrency, from: String): CryptoCurrencyLocal {
        return CryptoCurrencyLocal(
            model.cryptoId,
            from,
            model.name,
            model.icon,
            model.rate,
            model.time
        )
    }

    fun fromDomainList(model: List<CryptoCurrency>, from: String): List<CryptoCurrencyLocal> {
        return model.map {
            fromDomain(it, from)
        }
    }
}
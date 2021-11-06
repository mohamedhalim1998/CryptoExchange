package com.mohamed.halim.essa.cryptoexchange.data.network.model

import com.mohamed.halim.essa.cryptoexchange.data.domain.cryptocurrency.CryptoCurrency
import com.mohamed.halim.essa.cryptoexchange.data.domain.cryptocurrency.CryptoDomainMapper
import com.mohamed.halim.essa.cryptoexchange.utils.IconsData
import com.mohamed.halim.essa.cryptoexchange.utils.IsoTimeUtils

object CryptoDtoToDomainMapper : CryptoDomainMapper<CryptoCurrencyDto> {
    override fun toDomain(model: CryptoCurrencyDto): CryptoCurrency {
        return CryptoCurrency(
            model.currencyInfo.currencyId,
            model.currencyInfo.name,
            IconsData.icons[model.currencyInfo.currencyId]
                ?: IconsData.icons["USD"]!!,
            model.rateInfo.rate,
            IsoTimeUtils.fromIso(model.rateInfo.time)
        )
    }

    override fun toDomainList(model: List<CryptoCurrencyDto>): List<CryptoCurrency> {
        return model.map { e -> toDomain(e) }
    }


}
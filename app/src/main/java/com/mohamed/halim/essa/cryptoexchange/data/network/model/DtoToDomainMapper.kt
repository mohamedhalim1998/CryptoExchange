package com.mohamed.halim.essa.cryptoexchange.data.network.model

import com.mohamed.halim.essa.cryptoexchange.data.domain.CryptoCurrency
import com.mohamed.halim.essa.cryptoexchange.data.domain.DomainMapper
import com.mohamed.halim.essa.cryptoexchange.utils.IconsData
import com.mohamed.halim.essa.cryptoexchange.utils.IsoTimeUtils

object DtoToDomainMapper : DomainMapper<CryptoCurrencyDto> {
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
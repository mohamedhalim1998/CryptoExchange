package com.mohamed.halim.essa.cryptoexchange.data.network.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import android.content.res.Resources
import com.mohamed.halim.essa.cryptoexchange.R
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject



data class IconInfo(
    @SerializedName("asset_id")
    val currencyId: String,
    @SerializedName("url")
    val url: String,
)
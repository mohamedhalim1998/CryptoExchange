package com.mohamed.halim.essa.cryptoexchange

import android.app.Application
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mohamed.halim.essa.cryptoexchange.data.network.model.CurrencyInfo
import com.mohamed.halim.essa.cryptoexchange.data.network.model.IconInfo
import com.mohamed.halim.essa.cryptoexchange.utils.CurrencyDataUtils
import com.mohamed.halim.essa.cryptoexchange.utils.IconsData
import dagger.hilt.android.HiltAndroidApp

private const val TAG = "App"

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initIconsData()
        initCurrencyData()

    }


    private fun initIconsData() {
        val string = readFile("icons.json");
        val listType = object : TypeToken<ArrayList<IconInfo?>?>() {}.type
        val iconsList: List<IconInfo?> = Gson().fromJson(
            string, listType
        ) as List<IconInfo?>
        for (icon in iconsList) {
            if (icon != null)
                IconsData.icons[icon.currencyId] = icon.url
        }
    }

    private fun initCurrencyData() {
        val string = readFile("currencyData.json");
        val listType = object : TypeToken<ArrayList<CurrencyInfo?>?>() {}.type
        val currencies: List<CurrencyInfo?> = Gson().fromJson(
            string, listType
        ) as List<CurrencyInfo?>
        for (currency in currencies) {
            if (currency != null)
                CurrencyDataUtils.cryptoCurrencyMap[currency.currencyId] = currency
        }
    }


    private fun readFile(fileName: String): String {
        val stream = assets.open(fileName)
        val b = ByteArray(stream.available())
        stream.read(b)
        return String(b);
    }
}
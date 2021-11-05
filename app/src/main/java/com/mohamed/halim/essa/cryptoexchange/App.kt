package com.mohamed.halim.essa.cryptoexchange

import android.app.Application
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mohamed.halim.essa.cryptoexchange.data.network.model.IconInfo
import com.mohamed.halim.essa.cryptoexchange.utils.IconsData
import dagger.hilt.android.HiltAndroidApp

private const val TAG = "App"

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initIconsData()

    }

    private fun initIconsData() {
        val string = readFile();
        Log.d(TAG, "initIconsData: $string")
        val listType = object : TypeToken<ArrayList<IconInfo?>?>() {}.type
        val iconsList: List<IconInfo?> = Gson().fromJson(
            string, listType
        ) as List<IconInfo?>
        for (icon in iconsList) {
            if (icon != null)
                IconsData.icons[icon.currencyId] = icon.url
        }
    }

    private fun readFile(): String {
        val stream = assets.open("icons.json")
        val b = ByteArray(stream.available())
        stream.read(b)
        return String(b);
    }
}
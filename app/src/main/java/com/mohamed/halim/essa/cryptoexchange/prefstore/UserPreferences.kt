package com.mohamed.halim.essa.cryptoexchange.prefstore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


data class UserPreferences(val RealCurrency: String)

private const val STORE_NAME = "SETTINGS"
private val Context.dataStore by preferencesDataStore(
    name = STORE_NAME
)

class PrefsStoreManager(
    val context: Context,
) {
    private val userDataStore = context.dataStore

    val userPreferencesFlow: Flow<UserPreferences> = userDataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
            val showCompleted = preferences[PreferencesKeys.REAL_CURRENCY] ?: "USD"
            UserPreferences(showCompleted)
        }

    suspend fun updateShowCompleted(realCurrency: String) {
        userDataStore.edit { preferences ->
            preferences[PreferencesKeys.REAL_CURRENCY] = realCurrency
        }
    }
}

private object PreferencesKeys {
    val REAL_CURRENCY = stringPreferencesKey("REAL_CURRENCY")
}
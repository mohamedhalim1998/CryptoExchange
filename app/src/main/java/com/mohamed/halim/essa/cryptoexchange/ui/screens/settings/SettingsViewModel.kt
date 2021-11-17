package com.mohamed.halim.essa.cryptoexchange.ui.screens.settings

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mohamed.halim.essa.cryptoexchange.data.Repository
import com.mohamed.halim.essa.cryptoexchange.prefstore.PrefsStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: Repository,
    private val prefsStoreManager: PrefsStoreManager,
    private val state: SavedStateHandle,
) : ViewModel() {
    val userPreferences = prefsStoreManager.userPreferencesFlow.asLiveData(Dispatchers.IO)
    fun changeRealCurrency(realCurrency: String) {
        viewModelScope.launch {
            prefsStoreManager.updateRealCurrency(realCurrency)
        }
    }
}
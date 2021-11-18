package com.mohamed.halim.essa.cryptoexchange.ui.screens.settings

import androidx.lifecycle.*
import com.mohamed.halim.essa.cryptoexchange.data.Repository
import com.mohamed.halim.essa.cryptoexchange.prefstore.PrefsStoreManager
import com.mohamed.halim.essa.cryptoexchange.prefstore.UserPreferences
import com.mohamed.halim.essa.cryptoexchange.utils.listFromString
import com.mohamed.halim.essa.cryptoexchange.utils.stringFromList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: Repository,
    private val prefsStoreManager: PrefsStoreManager,
    private val state: SavedStateHandle,
) : ViewModel() {
    private val _userPreferences = MutableLiveData<UserPreferences>()
    val userPreferences: LiveData<UserPreferences>
        get() = _userPreferences
    private var cryptoCurrencies = mutableSetOf<String>()
    private val _query = MutableLiveData<String>()
    val query: LiveData<String>
        get() = _query

    init {
        viewModelScope.launch {
            prefsStoreManager.userPreferencesFlow.collect {
                _userPreferences.value = it
                cryptoCurrencies = listFromString(it.cryptoCurrencies).toMutableSet()
            }
        }
    }

    fun changeRealCurrency(realCurrency: String) {
        viewModelScope.launch {
            prefsStoreManager.updateRealCurrency(realCurrency)
        }
    }

    fun changeCryptoCurrencies() {
        viewModelScope.launch {
            prefsStoreManager.updateCryptoCurrency(stringFromList(cryptoCurrencies.toList()))
        }
    }

    fun addCryptoCurrency(id: String) {
        cryptoCurrencies.add(id)
    }

    fun deleteCryptoCurrency(id: String) {
        cryptoCurrencies.remove(id)
    }

    fun containCryptoCurrency(id: String): Boolean {
        return cryptoCurrencies.contains(id)
    }

    fun changeQuery(query: String) {
        _query.value = query;
    }
}
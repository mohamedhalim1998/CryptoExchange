package com.mohamed.halim.essa.cryptoexchange.ui.screens.cryptolist

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mohamed.halim.essa.cryptoexchange.data.Repository
import com.mohamed.halim.essa.cryptoexchange.data.domain.cryptocurrency.CryptoCurrency
import com.mohamed.halim.essa.cryptoexchange.prefstore.PrefsStoreManager
import com.mohamed.halim.essa.cryptoexchange.prefstore.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(
    private val repository: Repository,
    private val state: SavedStateHandle,
    private val prefsStoreManager: PrefsStoreManager,
) : ViewModel() {
    private val _cryptoRates = MutableLiveData<List<CryptoCurrency>>()
    val cryptoRates: LiveData<List<CryptoCurrency>>
        get() = _cryptoRates

    init {
        viewModelScope.launch {
            prefsStoreManager.userPreferencesFlow.collect { userPreference ->
                repository.getCurrentRate(
                    userPreference.RealCurrency,
                    userPreference.cryptoCurrencies.replace("\"","")
                ).collect {
                    _cryptoRates.value = it;
                }
            }
        }
    }


}
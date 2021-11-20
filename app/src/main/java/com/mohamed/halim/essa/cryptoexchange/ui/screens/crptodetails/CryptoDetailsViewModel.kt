package com.mohamed.halim.essa.cryptoexchange.ui.screens.crptodetails

import android.util.Log
import androidx.lifecycle.*
import com.mohamed.halim.essa.cryptoexchange.data.Repository
import com.mohamed.halim.essa.cryptoexchange.data.domain.cryptocurrency.CryptoCurrency
import com.mohamed.halim.essa.cryptoexchange.data.domain.rate.RateHistory
import com.mohamed.halim.essa.cryptoexchange.prefstore.PrefsStoreManager
import com.mohamed.halim.essa.cryptoexchange.prefstore.UserPreferences
import com.mohamed.halim.essa.cryptoexchange.utils.HistoryPeriod
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "CryptoDetailsViewModel"

@HiltViewModel
class CryptoDetailsViewModel @Inject constructor(
    private val repository: Repository,
    private val prefsStoreManager: PrefsStoreManager,
    private val state: SavedStateHandle,
) : ViewModel() {
    private val _rateHistory = MutableLiveData<List<RateHistory>>()
    val rateHistory: LiveData<List<RateHistory>>
        get() = _rateHistory

    private val _currentRate = MutableLiveData<CryptoCurrency>()
    val currentRate: LiveData<CryptoCurrency>
        get() = _currentRate
    private val _currentPeriod = MutableLiveData<HistoryPeriod>()
    val currentPeriod: LiveData<HistoryPeriod>
        get() = _currentPeriod

    private val _userPreferences = MutableLiveData<UserPreferences>();
    val userPreferences: LiveData<UserPreferences>
        get() = _userPreferences

    init {
        Log.d(TAG, "init: ${userPreferences.value}")
        viewModelScope.launch {
            prefsStoreManager.userPreferencesFlow.collect {
                _userPreferences.value = it
            }
        }
    }

    fun getCryptoHistoryHour(assetId: String) {
        viewModelScope.launch {
            repository.getCryptoHistoryHour(assetId).collect {
                _rateHistory.value = it
                _currentPeriod.value = HistoryPeriod.ONE_HOUR
                Log.d(TAG, "getCryptoHistoryHour: ${rateHistory.value?.size}")
            }
        }
    }

    fun getCryptoHistory12Hour(assetId: String) {
        viewModelScope.launch {
            repository.getCryptoHistory12Hour(assetId).collect {
                _rateHistory.value = it
                _currentPeriod.value = HistoryPeriod.TWELVE_HOUR
            }
        }
    }

    fun getCryptoHistoryDay(assetId: String) {
        viewModelScope.launch {
            repository.getCryptoHistoryDay(assetId).collect {
                _rateHistory.value = it
                _currentPeriod.value = HistoryPeriod.ONE_DAY
            }
        }
    }

    fun getCurrentRate(cryptoId: String) {
        viewModelScope.launch {
            repository.getCurrentRateOfOne(
                cryptoId,
                userPreferences.value?.RealCurrency ?: "USD"
            )
                .collect {
                    Log.d(TAG, "getCurrentRate: $it")
                    _currentRate.value = it
                }
        }
    }


}
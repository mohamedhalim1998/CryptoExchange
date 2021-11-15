package com.mohamed.halim.essa.cryptoexchange.ui.screens.crptodetails

import android.util.Log
import androidx.lifecycle.*
import com.mohamed.halim.essa.cryptoexchange.data.Repository
import com.mohamed.halim.essa.cryptoexchange.data.domain.cryptocurrency.CryptoCurrency
import com.mohamed.halim.essa.cryptoexchange.data.domain.rate.RateHistory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "CryptoDetailsViewModel"

@HiltViewModel
class CryptoDetailsViewModel @Inject constructor(
    private val repository: Repository,
    private val state: SavedStateHandle,
) : ViewModel() {
    private val _rateHistory = MutableLiveData<List<RateHistory>>()
    val rateHistory: LiveData<List<RateHistory>>
        get() = _rateHistory

    private val _currentRate = MutableLiveData<CryptoCurrency>()
    val currentRate : LiveData<CryptoCurrency>
        get() = _currentRate

    fun getCryptoHistoryHour(assetId: String) {
        viewModelScope.launch {
            repository.getCryptoHistoryHour(assetId).collect {
                _rateHistory.value = it
                Log.d(TAG, "getCryptoHistoryHour: ${rateHistory.value?.size}")
            }
        }
    }

    fun getCryptoHistory12Hour(assetId: String) {
        viewModelScope.launch {
            repository.getCryptoHistory12Hour(assetId).collect {
                _rateHistory.value = it
            }
        }
    }

    fun getCryptoHistoryDay(assetId: String) {
        viewModelScope.launch {
            repository.getCryptoHistoryDay(assetId).collect {
                _rateHistory.value = it
            }
        }
    }

    fun getCurrentRate(cryptoId: String) {
        viewModelScope.launch {
            repository.getCurrentRate(cryptoId).collect {
                _currentRate.value = it
            }
        }
    }


}
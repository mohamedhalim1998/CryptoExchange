package com.mohamed.halim.essa.cryptoexchange.ui.screens.crptodetails

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mohamed.halim.essa.cryptoexchange.data.Repository
import com.mohamed.halim.essa.cryptoexchange.data.domain.rate.RateHistoryDomain
import com.mohamed.halim.essa.cryptoexchange.utils.HistoryPeriods
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class CryptoDetailsViewModel @Inject constructor(
    private val repository: Repository,
    private val state: SavedStateHandle,
) : ViewModel() {
    private val _rateHistory = MutableLiveData<List<RateHistoryDomain>>()
    val rateHistory: LiveData<List<RateHistoryDomain>>
        get() = _rateHistory

    fun getCryptoHistoryHour(assetId: String) {
        viewModelScope.launch {
            repository.getCryptoHistoryHour(assetId).collect {
                _rateHistory.value = it
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


}